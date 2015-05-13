package com.dip.frontend.web.security;

import com.dip.common.constant.DipConstants;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class CookieCryptor {
	
	private static Logger LOG = Logger.getLogger(CookieCryptor.class);

//	@Resource(name="keyManagementServiceClient")
//	private KeyManagementWS keyManagementServiceClient;
	
	private boolean initialized = false;
	private byte[] key = null;
	private SecureRandom secureRandom;
	
	private void init() {
		if(!initialized) {
			try {
				key =  "DipCookieSecurityEncryptKey#32".getBytes(); //keyManagementServiceClient.getCookieKey();
				secureRandom = new SecureRandom();
				initialized = true;
			} catch (Exception e) {
				LOG.error("Can't get Cookie Key, will retry..", e);
			}
		}
	}
	
	public String decode(final String base64CookieValue) throws Exception {
		init();
		final BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESEngine()));
		byte[] iv = new byte[cipher.getBlockSize()];
		final byte[] input =  Base64.decodeBase64(base64CookieValue);
		System.arraycopy(input, 0, iv, 0, iv.length);
		cipher.init(false, new ParametersWithIV(new KeyParameter(key), iv));
		final byte[] output = new byte[cipher.getOutputSize(input.length - iv.length)];
		final int len = cipher.processBytes(input, iv.length, input.length - iv.length, output, 0);
		cipher.doFinal(output, len);        	  
		return new String(output);
	}
	
	public String encode(final String plainTextCookieValue) throws Exception {      
		init();
		final BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESEngine()));
		byte[] iv = new byte[cipher.getBlockSize()];
		secureRandom.nextBytes(iv);
		cipher.init(true, new ParametersWithIV(new KeyParameter(key), iv));
		byte[] input = plainTextCookieValue.getBytes(DipConstants.UTF_8);
		byte[] out = new byte[cipher.getOutputSize(input.length) + iv.length];
		System.arraycopy(iv, 0, out, 0, iv.length);
		final int len = cipher.processBytes(input, 0, input.length, out, iv.length);
		cipher.doFinal(out, len + iv.length);
		return Base64.encodeBase64String(out);
	}
}
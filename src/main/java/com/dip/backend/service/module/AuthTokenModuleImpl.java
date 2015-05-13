package com.dip.backend.service.module;

import com.dip.backend.config.IBackendProperties;
import com.dip.common.constant.UserType;
import com.dip.common.dto.AuthToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.StringTokenizer;


@Component("authTokenModule")
public class AuthTokenModuleImpl implements IAuthTokenModule {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IBackendProperties properties;

    @Override
    public AuthToken createToken(Map tokenParam) throws Exception {
        long curTime = System.currentTimeMillis();
        long expTime = getExpirationTime(curTime);
//        String token = null;

        StringBuffer buf = new StringBuffer();
        String expirationTime = String.valueOf(expTime);
        buf.append((String)tokenParam.get("USER_ID"));
        // add separator between user id and time component
        buf.append(":");
        buf.append( expirationTime  );

        final String userId = (String)tokenParam.get("USER_ID");
        final String principal = (String)tokenParam.get("PRINCIPAL");
        final UserType userRole = (UserType)tokenParam.get("USER_ROLE");


        AuthToken authToken = new AuthToken(buf.toString(), new Date(curTime), new Date(expTime));
        authToken.setPrincipal(principal);
        authToken.setUserId(userId);
        authToken.setUserRole(userRole.name());

        return  authToken;
    }

    @Override
    public AuthToken refreshToken(Map tokenParam) throws Exception {
        return createToken(tokenParam);
    }

    @Override
    public boolean isTokenValid(String userId, String principal, String token) throws Exception {
        String decUserId;		// decrypted userid
        String decTime;			// decrypted time

        String decString = token; // TODO: implement encryption if necessary
        long curTime = System.currentTimeMillis();


        log.debug("Token=" + token);
        log.debug("Parsing token" );

        // tokenize this string
        StringTokenizer tokenizer = new StringTokenizer(decString,":");
        if (tokenizer.hasMoreTokens()) {
            decUserId =  tokenizer.nextToken();
        }else {
            return false;
        }

        log.debug("- userId = " + decUserId );

        if (tokenizer.hasMoreTokens()) {
            decTime =  tokenizer.nextToken();
        }else  {
            return false;
        }

        if (!decUserId.equalsIgnoreCase(userId))
            return false;

        long ldecTime = Long.parseLong( decTime );

        log.debug("- Time found in Token => " + ldecTime );
        log.debug("- Time found in Token as date => " + new Date(ldecTime) );

        log.debug("- Token life in millis => " + getIdleTime());

        // decTime + idleTime = validTime for Token
        // long tokenValidTime = ldecTime + getIdleTime();

        log.debug("Valid token time=" + ldecTime + " curtime = " + curTime);
        log.debug("Token is valid till = " + new Date(ldecTime));
        log.debug("Current time=" + new Date(curTime));
        log.debug("Diff between token and curTime = " + (ldecTime - curTime));

        if ( curTime > ldecTime ) {
            //current time is greater then the allowed idle time

            log.debug("Token Failed time check"  );
            return false;
        }
        return true;
    }

    protected long getIdleTime(){
        return (properties.getTokenLife() * 60 * 1000);
    }

    private long getExpirationTime(long curTime) {
        long idleTime = 0l;
        idleTime = curTime + getIdleTime();
        return idleTime;
    }
}

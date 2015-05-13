package com.dip.frontend.web.security.http;

import com.dip.frontend.web.util.XSSUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

public class DipHttpServletRequest extends HttpServletRequestWrapper {
	
	private static Logger LOG = Logger.getLogger(DipHttpServletRequest.class);

	private boolean preventXSS;
	private Map<String, String[]> xssSafeParams;
	
	public DipHttpServletRequest(final HttpServletRequest request) {
		super(request);
	}

	public DipHttpServletRequest(final HttpServletRequest request, final boolean preventXSS) {
		super(request);
		this.preventXSS = preventXSS;
	}
	
	@Override
	public String getParameter(String name) {
		String parameter = null;
		String[] vals = (getParameterMap() != null) ? getParameterMap().get(name) : null; 
		
		if (vals != null && vals.length > 0) {
			parameter = vals[0];
		}
		
		return parameter;
	}

	@Override
	public String[] getParameterValues(String name) {
		return (getParameterMap() != null) ? getParameterMap().get(name) : null;
	}
	
	@Override
	public Enumeration<String> getParameterNames() {
		return Collections.enumeration((getParameterMap() != null) ? getParameterMap().keySet() : Collections.EMPTY_SET);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String,String[]> getParameterMap() {
		if(preventXSS) {
			if(xssSafeParams == null) {
				final Map<String, String[]> res = new HashMap<String, String[]>();
				final Map<String, String[]> originalQueryString = super.getParameterMap();
				if(originalQueryString!=null) {
					for (String key : (Set<String>) originalQueryString.keySet()) {
						String[] rawVals = originalQueryString.get(key);
						String[] snzVals = new String[rawVals.length];
						for (int i=0; i < rawVals.length; i++) {
							snzVals[i] = stripXSS(rawVals[i]);
							//if(LOG.isDebugEnabled()) {
							//	LOG.debug(String.format("Sanitized: %s to %s", rawVals[i], snzVals[i]));
							//}
						}
						res.put(stripXSS(key), snzVals);
					}
				}
				xssSafeParams = Collections.unmodifiableMap(res);
			}
			return xssSafeParams;
		} else {
			return super.getParameterMap();
		}
	}

	//TODO: Implement support for headers and cookies (override getHeaders and getCookies)
	
	private String stripXSS(String value) {
		return XSSUtils.stripXSS(value);
	}
}

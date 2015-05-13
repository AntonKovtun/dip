package com.sulin.frontend.web.util;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtils {

	public static Cookie getCookie(final HttpServletRequest request, final String cookieName) {
		Cookie retVal = null;
		if(request.getCookies() != null) {
			for(final Cookie cookie : request.getCookies()) {
				if(StringUtils.equals(cookie.getName(), cookieName)) {
					retVal = cookie;
					break;
				}
			}
		}
		return retVal;
	}
}

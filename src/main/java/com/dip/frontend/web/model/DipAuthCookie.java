package com.dip.frontend.web.model;

import org.apache.commons.lang.StringUtils;

import java.util.Date;

public class DipAuthCookie {

	private String principal;
	private String userId;
	private String userRole;
    private String token;
	private Date expriationTime;

	public DipAuthCookie(final String userId, final String principal, final String token, final String userRole,
						 final Date expriationTime) {
		this.userId = userId;
		this.userRole = userRole;
		this.principal = principal;
        this.token=token;
		this.expriationTime = expriationTime;
	}

	public String getUserId() {
		return userId;
	}


	public String getUserRole() {
		return userRole;
	}
	
	public String getPrincipal() {
		return principal;
	}

    public Date getExpriationTime() {
		return expriationTime;
	}

    public String getToken() {
        return token;
    }

    public boolean isExpired() {
		boolean retVal = false;
		if(expriationTime != null) {
			retVal = new Date().after(expriationTime);
		}
		return retVal;
	}

	public String tokenizeValue() {
		if(expriationTime != null) {
			return String.format("%s|%s|%s|%s|%s", userId, principal, token, userRole, expriationTime.getTime());
		} else {
			return String.format("%s|%s|%s|%s", userId, principal, token, userRole);
		}
	}
	
	/**
	 * @param value - the decrypted Cookie Value
	 * @return - the token represented by the value
	 */
	public static DipAuthCookie getToken(final String value) {
        DipAuthCookie retVal = null;
		if(StringUtils.isNotBlank(value)) {
			final String[] split = StringUtils.split(value, "|");
			if(split != null && split.length >= 3) {
				final String userId = StringUtils.trimToNull(split[0]); /* is not null only when cookie renew is disabled */
				final String principal = StringUtils.trimToNull(split[1]);
                final String token = StringUtils.trimToNull(split[2]);
				final String userRole = StringUtils.trimToNull(split[3]);
				Date expriationTime = null;
				try {
					expriationTime = new Date(Long.valueOf(StringUtils.trimToNull(split[4])).longValue());
				} catch(Throwable e) {
					
				}
				retVal = new DipAuthCookie(userId, principal, token, userRole, expriationTime);
			}
		}
		return retVal;
	}

	@Override
	public String toString() {
		return String
				.format("DipAuthCookie [principal=%s, userId=%s, token=%s, userRole=%s, expriationTime=%s]",
						principal, userId, token, userRole, expriationTime);
	}
	
	
}

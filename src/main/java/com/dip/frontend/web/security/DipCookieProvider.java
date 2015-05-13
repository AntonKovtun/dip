package com.dip.frontend.web.security;

import com.dip.backend.service.IAuthenticationService;
import com.dip.common.dto.AuthToken;
import com.dip.common.dto.AuthenticationResponse;
import com.dip.frontend.config.IFrontendProperties;
import com.dip.frontend.web.model.DipAuthCookie;
import com.dip.frontend.web.util.CookieUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class DipCookieProvider {

    private static Logger LOG = Logger.getLogger(DipCookieProvider.class);

    @Autowired
    private CookieCryptor cookieCryptor;

    @Autowired
    private IFrontendProperties frontendProperties;
    @Autowired
    private IAuthenticationService authenticationService;

    private static final String COOKIE_NAME = "DIP_AUTH_TOKEN";
    private static final String COOKIE_RENEWED_ATTR = "AUTH_COOKIE_RENEWED";
    private static final String COOKIE_RENEW_FAILED = "AUTH_COOKIE_RENEW_FAILED";



//    @Value("${org.openiam.auth.cookie.domain}")
//    private String cookieDomain;

//    @Value("${org.openiam.ui.auth.cookie.encryption.enabled}")
    private boolean cookieEncryptionEnabled=true;
    
    //@Value("${org.openiam.ui.auth.cookie.include.timestamp}")
    //private boolean includeTimestamp;

    /**
     * Called to renew the current Authentication Token
     * 
     * @param request
     *            - the current HttpServletRequest
     * @return - the new OpenIAMAuthCookie, representing the identity of the
     *         User
     */
    public DipAuthCookie renew(final HttpServletRequest request, final HttpServletResponse response) {
        DipAuthCookie retVal = null;
        final DipAuthCookie oldCookie = decrypt(request);
        String user_Id = this.getUserId(request);
//        if (!ProxyFilter.isProxyRequest(request)) {
//            LOG.debug("is not a proxy request - renewing cookie");
            if (oldCookie != null) {

                final AuthenticationResponse authResponse = authenticationService.renewToken(oldCookie.getPrincipal(), oldCookie.getToken(), user_Id);
                if(oldCookie.isExpired()) {
                    authResponse.fail();
                }

                if (authResponse != null && authResponse.isSuccess()) {
                    final AuthToken authToken = (AuthToken) authResponse.getAuthToken();
                    if (authToken != null) {
                        final String token = authToken.getToken();
                        final String userRole = authToken.getUserRole();
                        final String userId = authToken.getUserId();
                        final String principal = authToken.getPrincipal();
                        try {
                            //retVal = new OpenIAMAuthCookie(userId, principal, token, tokenType, (includeTimestamp) ? ssoToken.getExpirationTime() : null);
                            retVal = new DipAuthCookie(userId, principal, token, userRole, authToken.getExpirationTime());
                            encryptAndAdd(request, response, retVal, authToken);
                            request.setAttribute(COOKIE_RENEWED_ATTR, retVal);
                        } catch (Throwable e) {
                            LOG.warn(String.format("Can't encrypt cookie", authToken), e);
                        }
                    }
                } else {
                    request.setAttribute(COOKIE_RENEW_FAILED, true);
                }
            }
//        } else {
//            LOG.debug("Is proxy request - using cookie from proxy");
//            retVal = oldCookie;
//            request.setAttribute(COOKIE_RENEWED_ATTR, retVal);
//        }
        return retVal;
    }

    public boolean cookieRenewFailedForThisRequest(final HttpServletRequest request) {
        return Boolean.TRUE.equals(request.getAttribute(COOKIE_RENEW_FAILED));
    }

    public DipAuthCookie getTokenForCurrentRequest(final HttpServletRequest request) {
        return (DipAuthCookie) request.getAttribute(COOKIE_RENEWED_ATTR);
    }

    /**
     * Removes the AuthCookie from your request
     * 
     * @param response
     *            - HttpServletResponse of this request
     */
    public void invalidate(final HttpServletRequest request, final HttpServletResponse response) {
        final Cookie cookie = CookieUtils.getCookie(request, COOKIE_NAME);
        if (cookie != null) {
            cookie.setDomain(getCookieDomain());
            cookie.setValue(null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        final Cookie cookieOrganizationId = CookieUtils.getCookie(request, "organizationId");
        if (cookieOrganizationId != null) {
            cookieOrganizationId.setDomain(getCookieDomain());
            cookieOrganizationId.setValue(null);
            cookieOrganizationId.setMaxAge(0);
            cookieOrganizationId.setPath("/");
            response.addCookie(cookieOrganizationId);
        }
    }

    public String getUserId(final HttpServletRequest request) {
        String userId = null;
        final DipAuthCookie token = decrypt(request);
        if (token != null) {
            userId = token.getUserId();
        }
        return userId;
    }
    public String getUserRole(final HttpServletRequest request) {
        String userRole = null;
        final DipAuthCookie token = decrypt(request);
        if (token != null) {
            userRole = token.getUserRole();
        }
        return userRole;
    }

    public String getOrganizationId(final HttpServletRequest request) {
        String organizationId = null;
        Cookie cookie = CookieUtils.getCookie(request, "organizationId");
        if (cookie != null) {
            organizationId = cookie.getValue();
        }
        return organizationId;
    }

    public String getPrincipal(final HttpServletRequest request) {
        String principal = null;
        final DipAuthCookie cookie = this.decrypt(request);
        if (cookie != null) {
            principal = cookie.getPrincipal();
        }
        return principal;
    }

    /**
     * 
     * @param request
     *            - the current HttpServletRequest
     * @return - the current OpenIAMAuthCookie - does NOT renew the token
     */
    private DipAuthCookie decrypt(final HttpServletRequest request) {
        DipAuthCookie retVal = null;
        final Cookie cookie = CookieUtils.getCookie(request, COOKIE_NAME);

        if (cookie != null && cookie.getMaxAge() != 0) {
            try {
                final String cookieValue = (cookieEncryptionEnabled) ? cookieCryptor.decode(cookie.getValue()) : cookie.getValue();
                retVal = DipAuthCookie.getToken(cookieValue);
            } catch (Throwable e) {
                LOG.warn(String.format("Can't decrypt cookie", cookie.getValue()), e);
            }
        }

        return retVal;
    }

    public void setAuthInfo(final HttpServletRequest request, final HttpServletResponse response, final String principal,
                            final AuthenticationResponse authResponse) throws Exception {
        final String userId = authResponse.getUserId();

        final AuthToken authToken = authResponse.getAuthToken();
        final DipAuthCookie token = new DipAuthCookie(userId, principal, authToken.getToken(), authToken.getUserRole(), authToken.getExpirationTime());
        encryptAndAdd(request, response, token, authToken);
    }

    private void encryptAndAdd(final HttpServletRequest request, final HttpServletResponse response, final DipAuthCookie token,
                               final AuthToken authToken) throws Exception {
        final int seconds = Long.valueOf((authToken.getExpirationTime().getTime() - new Date().getTime()) / 1000).intValue();

        final String cookieValue = (cookieEncryptionEnabled) ? cookieCryptor.encode(token.tokenizeValue()) : token.tokenizeValue();
        final Cookie cookie = new Cookie(COOKIE_NAME, cookieValue);
        if(frontendProperties.expireOnBroserClose()) {
        	cookie.setMaxAge(-1);
        } else {
        	cookie.setMaxAge(seconds);
        }
        cookie.setPath("/");
        cookie.setSecure(StringUtils.equalsIgnoreCase("https", request.getScheme()));
        cookie.setDomain(getCookieDomain());
        response.addCookie(cookie);
    }

    private String getCookieDomain() {
        return frontendProperties.getCookieDomain();
    }

    public void setOrganizationId(final HttpServletRequest request, final HttpServletResponse response, final String cookieValue) throws Exception {


        final Cookie cookie = new Cookie("organizationId", cookieValue);
        if(frontendProperties.expireOnBroserClose()) {
            cookie.setMaxAge(-1);
        } else {
            cookie.setMaxAge(999999); //TODO make second like encryptAndAdd(...)
        }
        cookie.setPath("/");
        cookie.setSecure(StringUtils.equalsIgnoreCase("https", request.getScheme()));
        cookie.setDomain(getCookieDomain());
        response.addCookie(cookie);

    }
}

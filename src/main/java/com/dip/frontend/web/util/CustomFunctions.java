package com.dip.frontend.web.util;

import com.dip.common.constant.UserType;
import com.dip.frontend.config.IFrontendProperties;
import com.dip.frontend.web.security.DipCookieProvider;
import com.dip.frontend.web.security.SecurityUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by: Alexander Duckardt
 * Date: 8/15/14.
 */
public class CustomFunctions {
    private static final Logger LOG = Logger.getLogger(CustomFunctions.class);

    private static DipCookieProvider cookieProvider;
    private static IFrontendProperties frontendProperties;
    private static boolean isInitialized=false;

    public static boolean hasAccess(HttpServletRequest httpRequest, String uri) {
        if(!isInitialized){
            init(httpRequest);
        }

        if(cookieProvider!=null){
            String roleId = cookieProvider.getUserRole(httpRequest);
            LOG.debug("RoleId: " + roleId);

            if(roleId!=null)
                return SecurityUtils.isURLAllowed(UserType.valueOf(roleId), httpRequest, uri);
            else
                return SecurityUtils.isURLOpen(httpRequest, uri);
        }
        return false;
    }

    public static boolean hasRole(HttpServletRequest httpRequest, String role) {
        if(!isInitialized){
            init(httpRequest);
        }

        if(cookieProvider!=null){
            String roleId = cookieProvider.getUserRole(httpRequest);
            LOG.debug("RoleId: " + roleId);

            if(roleId!=null)
                return roleId.equals(role);
        }
        return false;
    }
    public static boolean isAuthenticated(HttpServletRequest httpRequest) {
        if(!isInitialized){
            init(httpRequest);
        }

        if(cookieProvider!=null){
            String userId = cookieProvider.getUserId(httpRequest);
            LOG.debug("UserId: " + userId);
            return StringUtils.isNotBlank(userId);
        }
        return false;
    }

    private static void init(HttpServletRequest request){
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(
                request.getServletContext());
        frontendProperties = ctx.getBean(IFrontendProperties.class);
        cookieProvider = ctx.getBean(DipCookieProvider.class);
        isInitialized=true;
    }
}

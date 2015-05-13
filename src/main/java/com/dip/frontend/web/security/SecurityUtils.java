package com.sulin.frontend.web.security;

import com.sulin.common.constant.UserType;
import com.sulin.frontend.config.IFrontendProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by: Alexander Duckardt
 * Date: 8/15/14.
 */
public class SecurityUtils {
    protected static final Logger log = LoggerFactory.getLogger(SecurityUtils.class);

    private static IFrontendProperties frontendProperties;
    private static boolean isInitialized=false;


    public static boolean isURLOpen(HttpServletRequest request) {
        return isURLOpen(request, request.getRequestURI());
    }

    public static boolean isURLAllowed(UserType userRole, HttpServletRequest request) {

        return isURLAllowed(userRole, request, request.getRequestURI());
    }


    public static boolean isURLOpen(HttpServletRequest request, String requestURI) {
        return isURLAllowed(UserType.Other, request, requestURI);
    }

    public static boolean isURLAllowed(UserType userRole, HttpServletRequest request, String requestURI) {
        if(!isInitialized){
            init(request);
        }
        log.debug("requestURI: {}",requestURI);

        String formattedURL = requestURI.replaceFirst(frontendProperties.getContextRoot(),"");
        log.debug("formatted requestURI: {}",formattedURL);

        if("".equals(formattedURL)
           || "/".equals(formattedURL))
            return true;
        if(UserType.SuperAdmin == userRole)
            return true;


        for(Privilege privilege: SulinPermissions.userPermissions[userRole.ordinal()].getPrivileges()){
            if(privilege.getURL().equals(formattedURL)){
                if("GET".equals(request.getMethod())
                   || privilege.isCanWrite())
                    return true;
            }
        }
        return false;
    }


    private static void init(HttpServletRequest request){
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(
                request.getServletContext());
        frontendProperties = ctx.getBean(IFrontendProperties.class);
        isInitialized=true;
    }
}

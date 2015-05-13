package com.dip.frontend.web.interceptor;

import com.dip.backend.service.IUserService;
import com.dip.common.constant.UserType;
import com.dip.common.dto.UserDto;
import com.dip.common.exception.BasicServiceException;
import com.dip.common.searchbean.UserSearchBean;
import com.dip.frontend.config.IFrontendProperties;
import com.dip.frontend.web.model.DipAuthCookie;
import com.dip.frontend.web.security.DipCookieProvider;
import com.dip.frontend.web.security.SecurityUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SecurityInterceptor  extends HandlerInterceptorAdapter {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DipCookieProvider cookieProvider;
    @Autowired
    private IFrontendProperties frontendProperties;

    @Autowired
    private IUserService userService;

    @SuppressWarnings("unchecked")
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        log.info("================ Security interceptor Handler =======================");
        final String userId = cookieProvider.getUserId(request);
        final String roleId = cookieProvider.getUserRole(request);
        final DipAuthCookie newCookie = cookieProvider.renew(request, response);
        if (!SecurityUtils.isURLOpen(request)){
            if(cookieProvider.cookieRenewFailedForThisRequest(request)) {
                cookieProvider.invalidate(request, response);
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            } else if(newCookie==null){
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }

            if(!SecurityUtils.isURLAllowed(UserType.valueOf(roleId),request)){
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        }

        if (StringUtils.isNotBlank(userId)) {

            UserSearchBean userSearchBean = new UserSearchBean();
            userSearchBean.addKey(userId);
            UserDto userDto = null;
            try {
                userDto = userService.search(userSearchBean).get(0);
            } catch (BasicServiceException e) {
                e.printStackTrace();
            }

            request.setAttribute("userName", userDto.getName());
            request.setAttribute("userId", userId);

        }
        return true;
    }

}

package com.sulin.frontend.web.controller;

import com.sulin.backend.service.IAuthenticationService;
import com.sulin.backend.service.IUserService;
import com.sulin.common.constant.ErrorCodes;
import com.sulin.common.dto.AuthenticationResponse;
import com.sulin.common.dto.UserDto;
import com.sulin.frontend.constant.SulinURL;
import com.sulin.frontend.web.model.CommonResponse;
import com.sulin.frontend.web.model.LoginModel;
import com.sulin.frontend.web.security.SulinCookieProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author anton
 * 
 */
@Controller
public class LoginController extends AbstractFrontendController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IAuthenticationService authenticationService;
    @Autowired
    private SulinCookieProvider cookieProvider;


    @RequestMapping(value = SulinURL.LOGIN, method = RequestMethod.GET)
    public String userView(HttpServletRequest request, Model model) {

        return "login-page";
    }

    @RequestMapping(value = SulinURL.LOGIN, method = RequestMethod.POST)
    public @ResponseBody
    CommonResponse<String> processLogin(HttpSession session, @RequestBody LoginModel loginModel,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        CommonResponse<String> commonResponse = new CommonResponse<String>();

        if (!loginModel.getEmail().isEmpty() || !loginModel.getPassword().isEmpty()) {

            AuthenticationResponse authenticationResponse = authenticationService.login(loginModel.getEmail(), loginModel.getPassword());
            if(authenticationResponse.isSuccess()){
                cookieProvider.setAuthInfo(request,response,loginModel.getEmail(), authenticationResponse);
            }

            commonResponse.setValue((authenticationResponse.isSuccess())?"success":"wrong");
            commonResponse.setUrl(response.encodeRedirectURL(frontendProperties.getContextRoot()));
            if (commonResponse.getValue().equals("success")){
                commonResponse.setUrl(response.encodeRedirectURL(frontendProperties.getContextRoot()));
            }

        } else {
            commonResponse.setErrorCode(ErrorCodes.OK);
        }

        return commonResponse;
    }

    @RequestMapping(value = SulinURL.LOGOUT, method = RequestMethod.GET)
    public String processLogout(HttpServletRequest request, HttpServletResponse response) {

        String userId = cookieProvider.getUserId(request);
        authenticationService.logout(userId);
        cookieProvider.invalidate(request, response);

        return "logout-page";
    }

}

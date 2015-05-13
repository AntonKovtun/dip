package com.dip.frontend.web.controller;

import com.dip.backend.config.IBackendProperties;
import com.dip.backend.service.*;
import com.dip.common.constant.ErrorCodes;
import com.dip.common.constant.UserType;
import com.dip.common.dto.UserDto;
import com.dip.common.exception.BasicServiceException;
import com.dip.common.searchbean.UserSearchBean;
import com.dip.frontend.constant.DipURL;
import com.dip.frontend.web.model.CommonResponse;
import com.dip.frontend.web.model.UserWebModel;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by: andrew
 */
@Controller
public class UserController extends AbstractFrontendController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IBackendProperties backendProperties;

    @RequestMapping(value = DipURL.USER_LIST, method = RequestMethod.GET)
    public String userView(HttpServletRequest request, Model model, HttpSession session) {

        return "users";
    }

    @RequestMapping(value = DipURL.USER, method = RequestMethod.POST)
    public @ResponseBody
    CommonResponse<UserDto> postUser(HttpSession session, HttpServletRequest request, HttpServletResponse response,
            @RequestBody UserWebModel dataModel) throws Exception {
        CommonResponse<UserDto> commonResponse = new CommonResponse<UserDto>();

        String loginUserId = null;
        loginUserId = this.getUserId(request);
        commonResponse.setValue(userService.register(dataModel, loginUserId));

        return commonResponse;
    }

    @RequestMapping(value = DipURL.USER, method = RequestMethod.DELETE)
    public @ResponseBody
    CommonResponse<String> userDelete(HttpSession session, HttpServletRequest request, HttpServletResponse response,
            @RequestBody UserWebModel dataModel) throws Exception {

        userService.delete(dataModel.getId());

        CommonResponse<String> commonResponse = new CommonResponse<String>();

        commonResponse.setErrorCode(ErrorCodes.OK);

        return commonResponse;

    }

    @RequestMapping(value = DipURL.USER_SEARCH, method = RequestMethod.GET)
    public @ResponseBody
    CommonResponse<List<UserDto>> searchUser(HttpSession session, HttpServletRequest request,
            HttpServletResponse response,   @RequestParam(value = "groupId", required = false) String groupId,
                                            @RequestParam(value = "userType", required = false) UserType userType,
                                            @RequestParam(value = "id", required = false) String userId) throws Exception {


        String selectOrganization = this.cookieProvider.getOrganizationId(request);
        CommonResponse<List<UserDto>> commonResponse = new CommonResponse<List<UserDto>>();

        List<UserDto> userDataList = new ArrayList<UserDto>();
        List<UserDto> parentDataList = new ArrayList<UserDto>();
        UserSearchBean userSearchBean = new UserSearchBean();
        UserSearchBean parentSearchBean = new UserSearchBean();

        if (userType != null) {
            userSearchBean.setUserType(userType);
        }

        if (StringUtils.isNotBlank(userId)) {
            userDataList.add(0, userService.search(new UserSearchBean(userId)).get(0));
        }

        if (userType != null && userType.equals(UserType.SuperAdmin)) {
            userSearchBean.setUserType(UserType.SuperAdmin);
            try {
                userDataList = userService.search(userSearchBean);
            } catch (BasicServiceException e) {
                log.error(e.getMessage(), e);
            }
            commonResponse.setValue(userDataList);
        } else {
            try {
                userDataList = userService.search(userSearchBean);
            } catch (BasicServiceException e) {
                log.error(e.getMessage(), e);
            }
            commonResponse.setValue(userDataList);
        }

        return commonResponse;
    }

    @RequestMapping(value = DipURL.USER_PASSWORD, method = RequestMethod.GET)
    public @ResponseBody
    CommonResponse<String> userPassword(HttpSession session, HttpServletRequest request,
                                               HttpServletResponse response, @RequestParam(value = "id", required = false) String userId) throws Exception {

        CommonResponse<String> commonResponse = new CommonResponse<String>();

        UserSearchBean searchBean = new UserSearchBean(userId);

        String password = null;
        try {
            password = userService.search(searchBean).get(0).getPassword();
        } catch (BasicServiceException e) {
            for (int i = 0; i < 6; i++) {
                password = RandomStringUtils.randomAlphanumeric(6);
            }
        }

        commonResponse.setValue(password);

        return commonResponse;
    }

}

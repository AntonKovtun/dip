package com.dip.frontend.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dip.backend.config.IBackendProperties;
import com.dip.backend.service.IDipService;
import com.dip.common.constant.ErrorCodes;
import com.dip.common.dto.AuthenticationResponse;
import com.dip.common.dto.DipDto;
import com.dip.frontend.constant.DipURL;
import com.dip.frontend.web.model.CommonResponse;
import com.dip.frontend.web.model.DipModel;
import org.apache.commons.collections.list.LazyList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import scala.Int;

import java.util.ArrayList;
import java.util.List;


@Controller
public class IndexController extends AbstractFrontendController {

    @Autowired
    private IDipService dipService;

    @Autowired
    private IBackendProperties backendProperties;

    @RequestMapping(value = { DipURL.START, DipURL.INDEX }, method = RequestMethod.GET)
    public String getIndexView(HttpServletRequest request, Model model, HttpSession session) {

        return "index";
    }

    @RequestMapping(value = DipURL.DIPLOMA, method = RequestMethod.POST)
    public @ResponseBody
    CommonResponse<List<DipDto>> dip(HttpSession session, @RequestBody DipModel dipModel,
                                        HttpServletRequest request, HttpServletResponse response) throws Exception {
        CommonResponse<List<DipDto>> commonResponse = new CommonResponse<List<DipDto>>();

        List<DipDto> dipDtoList = new ArrayList<DipDto>();

        dipDtoList.add(dipService.mainMethod(dipModel));

        if (!dipModel.getSize().isEmpty()) {

            commonResponse.setValue(dipDtoList);

        } else {
            commonResponse.setErrorCode(ErrorCodes.OK);
        }

        return commonResponse;
    }
}

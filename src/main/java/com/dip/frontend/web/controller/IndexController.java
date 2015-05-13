package com.dip.frontend.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dip.backend.config.IBackendProperties;
import com.dip.frontend.constant.DipURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class IndexController extends AbstractFrontendController {

    @Autowired
    private IBackendProperties backendProperties;

    @RequestMapping(value = { DipURL.START, DipURL.INDEX }, method = RequestMethod.GET)
    public String getIndexView(HttpServletRequest request, Model model, HttpSession session) {

        return "index";
    }
}

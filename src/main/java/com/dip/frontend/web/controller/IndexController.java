package com.sulin.frontend.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sulin.backend.config.IBackendProperties;
import com.sulin.frontend.constant.SulinURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by: Alexander Duckardt Date: 7/6/14.
 */
@Controller
public class IndexController extends AbstractFrontendController {

    @Autowired
    private IBackendProperties backendProperties;

    @RequestMapping(value = { SulinURL.START, SulinURL.INDEX }, method = RequestMethod.GET)
    public String getIndexView(HttpServletRequest request, Model model, HttpSession session) {

        return "index";
    }
}

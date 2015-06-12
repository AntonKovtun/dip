package com.dip.frontend.web.controller;

import com.dip.frontend.constant.DipURL;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by anton on 01.06.15.
 */
@Controller
public class DiplomaController extends AbstractFrontendController {

    @RequestMapping(value = DipURL.DIPLOMA, method = RequestMethod.GET)
    public String userView(HttpServletRequest request, Model model, HttpSession session) {



        return "diploma";
    }
}

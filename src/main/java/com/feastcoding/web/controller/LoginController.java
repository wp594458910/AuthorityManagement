package com.feastcoding.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * Creator : peng
 * Date : 2018-03-09
 * Time : 14:13
 */
@Controller
public class LoginController {
    @RequestMapping(value = "/login")
    public String showLoginForm(HttpServletRequest req, Model model) {
        String exceptionClassName = (String)req.getAttribute("shiroLoginFailure");
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        model.addAttribute("error", error);
        return "login";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request, Model model)
    {
        //判断用户是否登录
        if (SecurityUtils.getSubject().isAuthenticated())
        {
            return "redirect:home";
        }
        return "login";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model)
    {
        return "index";
    }
}

package com.daily.controller;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * Creator : peng
 * Date : 2018-03-19
 * Time : 14:25
 */
@Controller
public class LoginController {

    @RequestMapping("admin.do")
    public String admin() {
        return "login";
    }

    @RequestMapping("success.do")
    public String success(ModelMap model) {
        return "index";
    }

    @RequestMapping("login.do")
    public String login(HttpServletRequest req, ModelMap model) {
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

    @RequestMapping("logout.do")
    public String logout(){
        return "login";
    }
}

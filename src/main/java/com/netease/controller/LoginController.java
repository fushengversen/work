package com.netease.controller;

import com.netease.pojo.User;
import com.netease.util.Response;
import com.netease.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
    @Resource
    public UserService userService;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping(value = "/api/login")
    @ResponseBody
    public Map login(String username, String password, HttpSession session){
        User user = userService.userLogin(username, password);
        if (null == user)   return Response.build(300, "用户名或者密码错误", false);
        session.setAttribute("user", user.getUsername());
        return Response.build(200, "登陆成功", true);

    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "index";
    }
}

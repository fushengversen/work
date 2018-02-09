package com.netease.controller;

import com.netease.pojo.User;
import com.netease.responseUtil.Response;
import com.netease.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
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
    public Map login(String userName, String password, HttpSession session){
        System.out.println("enter login controller");
        User user = userService.userLogin(userName, password);
        if (null == user)   return Response.build(300, "用户名或者密码错误", false);
        session.setAttribute("user", user.getUsername());
        return Response.build(200, "登陆成功", true);

    }
}

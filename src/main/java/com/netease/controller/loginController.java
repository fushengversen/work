package com.netease.controller;

import com.netease.mapper.UserMapper;
import com.netease.pojo.User;
import com.netease.pojo.UserExample;
import com.netease.responseUtil.Response;
import com.netease.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.netease.pojo.UserExample.Criteria;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class loginController {

    @Autowired
    private UserService userService;
    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    @ResponseBody
    public Response login(String userName, String password, HttpServletRequest request, HttpServletResponse response){
        System.out.println("enter login "+ userName + " "+ password);
        Response result = userService.userLogin(userName, password, request, response);
        return result;

    }
}

package com.netease.controller;

import com.netease.pojo.Item;
import com.netease.pojo.User;
import com.netease.service.ItemService;
import com.netease.util.Permission;
import com.netease.util.Role;
import jdk.incubator.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AccountController {
    @Resource
    private ItemService itemService;

    @Permission(permission = {Role.BUYER})
    @RequestMapping("/account")
    public ModelAndView account(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) session.getAttribute("user");
        List<Item> itemList = itemService.getItemsByUser(user);
        modelAndView.addObject("itemList", itemList);
        modelAndView.setViewName("account");
        return modelAndView;
    }
}

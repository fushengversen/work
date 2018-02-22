package com.netease.controller;

import com.netease.pojo.Item;
import com.netease.pojo.User;
import com.netease.service.ItemService;
import com.netease.util.Permission;
import com.netease.util.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Resource
    private ItemService itemService;

    @RequestMapping("/")
    public ModelAndView showIndex(){
        ModelAndView modelAndView = new ModelAndView();
        List<Item> itemList = itemService.getAllItems();
        modelAndView.addObject("itemList", itemList);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @Permission(permission = Role.BUYER)
    @RequestMapping("/index")
    public ModelAndView index(int role, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        if(role == 0)   request.getRequestDispatcher("/");
        modelAndView.addObject("role", role);
        User user = (User) request.getSession().getAttribute("user");
        List<Item> itemList = itemService.getItemsWithoutUser(user);
        modelAndView.addObject("itemList", itemList);
        modelAndView.setViewName("index");
        return modelAndView;
    }

}

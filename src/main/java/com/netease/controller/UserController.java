package com.netease.controller;

import com.netease.pojo.Item;
import com.netease.pojo.User;
import com.netease.service.ItemService;
import com.netease.util.Identity;
import com.netease.util.Response;
import com.netease.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private ItemService itemService;



    @RequestMapping("/system/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/system")
    public String index(@RequestParam(value = "role", required = false, defaultValue = "0")int role, ModelMap modelMap) {
        List<Item> allItems = itemService.getAllItems();
        modelMap.addAttribute("role",role);
        modelMap.addAttribute("allItems", allItems);
        return "index";
    }

    @RequestMapping(value = "/api/login")
    @ResponseBody
    public Map login(String username, String password, HttpSession session){
        User user = userService.userLogin(username, password);
        if (null == user)   return Response.build(300, "用户名或者密码错误", false);
        session.setAttribute("user", user);
        return Response.build(200, "登陆成功", true);

    }

    @RequestMapping(value = "system/api/upload")
    @ResponseBody
    public Map upload(){
        return Response.build(200, "登陆成功", true);
    }

    @RequestMapping("/system/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/system";
    }



    @RequestMapping("/system/show")
    public String show(@RequestParam(value = "id") Integer id, ModelMap modelMap){
        Item item = itemService.getItemById(id);
        modelMap.addAttribute("item", item);
        return "show";
    }


}

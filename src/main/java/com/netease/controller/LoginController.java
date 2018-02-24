package com.netease.controller;

import com.netease.pojo.Item;
import com.netease.pojo.User;
import com.netease.service.ItemService;
import com.netease.util.Response;
import com.netease.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping("/system")
public class LoginController {
    @Resource
    private UserService userService;

    @Resource
    private ItemService itemService;
//    private boolean hasLogined(HttpSession session) {
//        User user = (User) session.getAttribute("user");
//        return user != null;
//    }
//
//    private boolean isBuyer(HttpSession session) {
//        User user = (User) session.getAttribute("user");
//        return user != null && user.getRole() == 1;
//    }
//
//    private boolean isSeller(HttpSession session) {
//        User user = (User) session.getAttribute("user");
//        return user != null && user.getRole() == 0;
//    }

    @RequestMapping("/system/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/system")
    public String index(ModelMap modelMap) {
        List<Item> allItems = itemService.getAllItems();
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

    @RequestMapping("/system/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/system";
    }
//
//    @RequestMapping("/error")
//    public String error(){
//        return "error";
//    }
//
//    @RequestMapping("/publish")
//    public String publish(HttpSession session) {
//        if (!isSeller(session)) {
//            return "error";
//        }
//        return "publish";
//    }
}

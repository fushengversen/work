package com.netease.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netease.pojo.Cart;
import com.netease.pojo.Item;
import com.netease.pojo.User;
import com.netease.service.CartService;
import com.netease.service.ItemService;
import com.netease.util.Identity;
import com.netease.util.Response;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AccountController {

    @Resource
    private CartService cartService;

    @Resource
    private ItemService itemService;

    @RequestMapping("/system/account")
    public String account(HttpSession session){
        if (!Identity.isBuyer(session))  return "error";
        return "account";
    }

    @RequestMapping("/system/api/buy")
    @ResponseBody
    public Map buy(){
        //TODO
        //增加商品卖出和购物车加入逻辑
        return Response.build(200, "购买成功", true);
    }
}

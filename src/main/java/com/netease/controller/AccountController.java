package com.netease.controller;

import com.netease.pojo.Account;
import com.netease.service.AccountService;
import com.netease.service.CartService;
import com.netease.service.ItemService;
import com.netease.util.Identity;
import com.netease.util.Response;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class AccountController {

    @Resource
    private CartService cartService;

    @Resource
    private ItemService itemService;

    @Resource
    private AccountService accountService;

    @RequestMapping("/system/account")
    public String account(HttpSession session, ModelMap modelMap){
        if (!Identity.isBuyer(session))  return "error";
        List<Account> accounts = accountService.accountList();
        int totalPrice = 0;
        for (Account account : accounts){
            totalPrice += account.getPrice();
        }
        modelMap.addAttribute("accounts", accounts);
        modelMap.addAttribute("totalPrice", totalPrice);
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

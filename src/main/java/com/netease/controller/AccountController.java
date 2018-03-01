package com.netease.controller;

import com.netease.pojo.Account;
import com.netease.pojo.Sold;
import com.netease.pojo.User;
import com.netease.service.AccountService;
import com.netease.service.CartService;
import com.netease.service.ItemService;
import com.netease.util.Identity;
import com.netease.util.Response;
import javafx.scene.input.DataFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public Map buy(HttpSession session, @RequestBody List<Sold> soldList){

        if (!Identity.isBuyer(session))  return Response.build(300, "购买失败", false);
        User user = (User) session.getAttribute("user");
        List<Account> accounts = new ArrayList<>();

        for (Sold sold : soldList){
            Account account = new Account();
            account.setUserId(user.getId());
            account.setItemId(sold.getItemId());
            account.setPrice(sold.getPrice());
            account.setNum(sold.getNum());
            Date now = new Date();
            account.setPurchaseTime(new Timestamp(now.getTime()));
            accounts.add(account);
        }
        //加入已购买清单
        accountService.insertItemToAccount(accounts);
        //已卖出物品更新
        itemService.soldItems(soldList);
        //从购物车列表清除
        cartService.deleteAll();
        return Response.build(200, "购买成功", true);
    }

}

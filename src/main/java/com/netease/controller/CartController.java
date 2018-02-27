package com.netease.controller;

import com.netease.pojo.Cart;
import com.netease.service.CartService;
import com.netease.service.ItemService;
import com.netease.util.Identity;
import com.netease.util.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {
    @Resource
    private CartService cartService;

    @Resource
    private ItemService itemService;

    @RequestMapping("/addToCart")
    @ResponseBody
    public Map addToCart(@RequestBody Cart cart){
        cartService.addCart(cart);
        return Response.build(200, "添加购物车成功", true);
    }

    @RequestMapping("/system/settleAccount")
    public String cart(HttpSession session) {
        if (!Identity.isBuyer(session)) {
            return "error";
        }
        return "settleAccount";
    }

    @RequestMapping("/system/api/getCart")
    @ResponseBody
    public Response<List> getCart(){
        Response<List> response = new Response<>();
        List<Cart> itemList = cartService.itemList();
        response.setData(itemList);
        response.setCode(200);
        response.setResult(true);
        response.setMessage("获取购物车商品列表成功");
        return response;
    }
}

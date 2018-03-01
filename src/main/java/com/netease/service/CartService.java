package com.netease.service;

import com.netease.pojo.Cart;

import java.util.List;

public interface CartService {
    List<Cart> cartsByUserId(int userId);

    List<Cart> itemList();

    int addCart(Cart cart);

    int deleteAll();

//    int addItemsToCart(List<Cart> carts);

}

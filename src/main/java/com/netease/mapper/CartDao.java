package com.netease.mapper;

import com.netease.pojo.Cart;

import java.util.List;

public interface CartDao {
    List<Cart> cartsByUserId(int userId);

    List<Cart> itemList();

    int addCart(Cart cart);

}

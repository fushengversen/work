package com.netease.service.impl;

import com.netease.mapper.CartDao;
import com.netease.pojo.Cart;
import com.netease.service.CartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Resource
    private CartDao cartDao;

    @Override
    public List<Cart> cartsByUserId(int userId) {
        return cartDao.cartsByUserId(userId);
    }

    @Override
    public List<Cart> itemList() {
        return cartDao.itemList();
    }

    @Override
    public int addCart(Cart cart) {
        return cartDao.addCart(cart);
    }
}

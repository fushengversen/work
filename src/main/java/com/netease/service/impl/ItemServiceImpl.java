package com.netease.service.impl;

import com.netease.mapper.ItemDao;
import com.netease.pojo.Item;
import com.netease.service.ItemService;

import javax.annotation.Resource;

public class ItemServiceImpl implements ItemService {

    @Resource
    private ItemDao itemDao;
    @Override
    public Item getItemById(int id) {
        return itemDao.getItemById(id);
    }
}

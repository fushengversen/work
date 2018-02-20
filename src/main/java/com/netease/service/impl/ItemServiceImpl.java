package com.netease.service.impl;

import com.netease.mapper.ItemDao;
import com.netease.pojo.Item;
import com.netease.pojo.User;
import com.netease.service.ItemService;

import javax.annotation.Resource;
import java.util.List;

public class ItemServiceImpl implements ItemService {

    @Resource
    private ItemDao itemDao;
    @Override
    public Item getItemById(int id) {
        return itemDao.getItemById(id);
    }

    @Override
    public boolean addItem(Item item) {
        if(null == item.getTitle() || item.getTitle().length() < 2 || item.getTitle().length() > 80)   return false;
        if(null == item.getDescription() || item.getDescription().length() < 2 || item.getDescription().length() > 140){
            return false;
        }
        itemDao.addItem(item);
        return item.getId() != 0;
    }

    @Override
    public boolean updateItem(Item item) {
        if(null == item.getTitle() || item.getTitle().length() < 2 || item.getTitle().length() > 80)   return false;
        if(null == item.getDescription() || item.getDescription().length() < 2 || item.getDescription().length() > 140){
            return false;
        }
        int result = itemDao.updateItem(item);
        return result != 0;
    }

    @Override
    public boolean deleteItem(int id) {
        return itemDao.deleteItemById(id) != 0;
    }

    @Override
    public List<Item> getAllItems() {
        return itemDao.getAllItems();
    }

    @Override
    public List<Item> getItemsByUser(User user) {
        return itemDao.getItemsByUser(user);
    }

    @Override
    public List<Item> getItemsWithoutUser(User user) {
        return itemDao.getItemsWithoutUser(user);
    }
}

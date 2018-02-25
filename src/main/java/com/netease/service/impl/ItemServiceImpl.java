package com.netease.service.impl;

import com.netease.mapper.ItemDao;
import com.netease.pojo.Item;
import com.netease.pojo.User;
import com.netease.service.ItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    private ItemDao itemDao;
    @Override
    public Item getItemById(int id) {
        return itemDao.getItemById(id);
    }

    @Override
    public int addItem(Item item) {
        itemDao.addItem(item);
        return item.getId() ;
    }

    @Override
    public boolean updateItem(int id, Item item) {
        int result = itemDao.updateItem(id, item);
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

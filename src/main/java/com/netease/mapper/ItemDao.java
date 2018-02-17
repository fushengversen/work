package com.netease.mapper;

import com.netease.pojo.Item;
import com.netease.pojo.User;

import java.util.List;

public interface ItemDao {
    Item getItemById(int id);

    void addItem(Item item);

    int updateItem(Item item);

    int deleteItemById(int id);

    List<Item> getItemsByUser(User user);

    List<Item> getItemWithoutUser(User user);
    
    List<Item> getAllItems();
}

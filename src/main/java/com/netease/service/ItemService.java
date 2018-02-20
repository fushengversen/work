package com.netease.service;

import com.netease.pojo.Item;
import com.netease.pojo.User;

import java.util.List;

public interface ItemService {
    Item getItemById(int id);

    boolean addItem(Item item);

    boolean updateItem(Item item);

    boolean deleteItem(int id);

    List<Item> getAllItems();

    List<Item> getItemsByUser(User user);

    List<Item> getItemsWithoutUser(User user);
}

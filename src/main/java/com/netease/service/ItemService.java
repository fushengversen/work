package com.netease.service;

import com.netease.pojo.Item;
import com.netease.pojo.Sold;
import com.netease.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;
public interface ItemService {
    Item getItemById(int id);

    int addItem(Item item);

    boolean updateItem(int id, Item item);

    boolean deleteItem(int id);

    int soldItems(List<Sold> soldList);

    List<Item> getAllItems();

    List<Item> getItemsByUser(User user);

    List<Item> getItemsWithoutUser(User user);
}

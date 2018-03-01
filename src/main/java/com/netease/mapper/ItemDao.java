package com.netease.mapper;

import com.netease.pojo.Item;
import com.netease.pojo.Sold;
import com.netease.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemDao {
    Item getItemById(int id);

    void addItem(Item item);

    int updateItem(@Param("id") int id, @Param("item") Item item);

    int deleteItemById(int id);

    int soldItem(Sold sold);

    List<Item> getItemsByUser(User user);

    List<Item> getItemsWithoutUser(User user);
    
    List<Item> getAllItems();
}

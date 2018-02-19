package com.netease.mapper;

import com.netease.pojo.Deal;

public interface DealDao {
    void addDeal(Deal deal);
    int dealDeal(int id);
    int updateDeal(int id);
    Deal getDealByProductId(int pid);
}

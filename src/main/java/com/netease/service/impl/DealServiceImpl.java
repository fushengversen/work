package com.netease.service.impl;

import com.netease.mapper.DealDao;
import com.netease.pojo.Deal;
import com.netease.service.DealService;

import javax.annotation.Resource;

public class DealServiceImpl implements DealService {
    @Resource
    DealDao dealDao;
    @Override
    public boolean addDeal(Deal deal) {
        dealDao.addDeal(deal);
        return deal.getId() != 0;
    }
}

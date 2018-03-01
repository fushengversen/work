package com.netease.service.impl;

import com.netease.mapper.AccountDao;
import com.netease.pojo.Account;
import com.netease.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDao accountDao;

    @Override
    public Account getAccountByUserId(int id) {
        return null;
    }

    @Override
    public List<Account> accountList() {
        return accountDao.accountList();
    }

    @Override
    public int insertItemToAccount(List<Account> accounts) {
        return accountDao.insertItemsToAccount(accounts);
    }
}

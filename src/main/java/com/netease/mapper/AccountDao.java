package com.netease.mapper;

import com.netease.pojo.Account;

import java.util.List;

public interface AccountDao {
    Account getAccountByUserId(int id);
    
    List<Account> accountList();
}

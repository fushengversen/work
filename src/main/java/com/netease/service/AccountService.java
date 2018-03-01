package com.netease.service;

import com.netease.pojo.Account;

import java.util.List;

public interface AccountService {
    Account getAccountByUserId(int id);

    List<Account> accountList();

    int insertItemToAccount(List<Account> accounts);
}

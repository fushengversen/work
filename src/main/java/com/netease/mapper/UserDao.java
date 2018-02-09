package com.netease.mapper;

import com.netease.pojo.User;

public interface UserDao {
    public User getUser(String userName, String password);
}

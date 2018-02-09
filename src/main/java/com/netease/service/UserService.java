package com.netease.service;

import com.netease.pojo.User;

import org.springframework.stereotype.Repository;

@Repository(value="UserMapper")
public interface UserService {
    User userLogin(String username, String password);
}

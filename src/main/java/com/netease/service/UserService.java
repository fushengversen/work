package com.netease.service;

import com.netease.pojo.User;

public interface UserService {
    User userLogin(String username, String password);
}

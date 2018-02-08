package com.netease.service;

import com.netease.responseUtil.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
    Response userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);
}

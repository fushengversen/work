package com.netease.util;

import com.netease.pojo.User;

import javax.servlet.http.HttpSession;

public class Identity {
    public static boolean hasLogined(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return user != null;
    }

    public static boolean isBuyer(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return user != null && user.getRole() == 1;
    }

    public static boolean isSeller(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return user != null && user.getRole() == 0;
    }
}

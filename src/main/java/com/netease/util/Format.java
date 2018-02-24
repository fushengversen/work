package com.netease.util;

import java.util.HashMap;
import java.util.Map;

public class Format {
    public static Map<String, String> form(String form) {
        Map<String, String> result = new HashMap<>();
        String[] props = form.split("&");
        for (String prop : props) {
            int index = prop.indexOf('=');
            result.put(prop.substring(0, index), prop.substring(index + 1));
        }
        return result;
    }
}

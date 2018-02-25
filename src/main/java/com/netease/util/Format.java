package com.netease.util;

import com.netease.pojo.Item;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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

    public static Map<String, String> decodeString2Map(String form) throws UnsupportedEncodingException {
        Map<String, String> decode = Format.form(form);
        for(String key : decode.keySet()){
            String decodeString = URLDecoder.decode(decode.get(key), "utf-8");
            decode.put(key, decodeString);
        }
        return decode;
    }
}

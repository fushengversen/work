package com.netease.util;

import java.util.HashMap;
import java.util.Map;

public class Response<T> {

    private Integer code;

    private String message;

    private Boolean result;

    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static Map<String, Object> build(Integer code, String message, Boolean result){
        Map<String, Object> response = new HashMap<>();
        response.put("code", code);
        response.put("message", message);
        response.put("result", result);
        return response;
    }
}

package com.netease.util;

public enum  Role {
    VISTOR(-1), BUYER(0), SELLER(1);

    public int role;

    Role(int role){
        this.role = role;
    }

    public int getRole(){
        return role;
    }
}

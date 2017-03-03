package com.cc.user.bean;

/**
 * Created by xn032607 on 2017/2/22.
 */
public enum UserRoleEnum {
    ADMIN("admin"),
    VISITOR("visitor")
    ;

    private String role;

    UserRoleEnum(String desc) {
        this.role = desc;
    }

    public String getRole() {
        return role;
    }
}

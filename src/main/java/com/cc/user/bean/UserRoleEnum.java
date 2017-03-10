package com.cc.user.bean;

import java.util.HashMap;

/**
 * Created by xn032607 on 2017/2/22.
 */
public enum UserRoleEnum {
    ADMIN("ROLE_admin"),
    VISITOR("ROLE_visitor")
    ;

    private String role;

    private static HashMap<String,UserRoleEnum> userRoleEnumHashMap = new HashMap<>();

    UserRoleEnum(String desc) {
        this.role = desc;
    }

    static {
        for(UserRoleEnum userRoleEnum : values()) {
            userRoleEnumHashMap.put(userRoleEnum.role,userRoleEnum);
        }
    }

    public static UserRoleEnum getUserRole(String roleName) {
        return userRoleEnumHashMap.get(roleName);
    }

    public String getRole() {
        return role;
    }
}

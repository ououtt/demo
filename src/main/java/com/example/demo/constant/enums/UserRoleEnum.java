package com.example.demo.constant.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-17 22:46
 **/
public enum UserRoleEnum {

    ADMIN(1, "admin"),
    COMMON_USER(2, "普通用户");

    private int id;
    private String roleName;

    UserRoleEnum(int id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    private static Map<Integer, UserRoleEnum> idMap = Maps.newHashMap();

    static {
        for (UserRoleEnum userRoleEnum : UserRoleEnum.values()) {
            idMap.put(userRoleEnum.getId(), userRoleEnum);
        }
    }

    public static UserRoleEnum parseId(int id) {
        return idMap.get(id);
    }

    public int getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }
}

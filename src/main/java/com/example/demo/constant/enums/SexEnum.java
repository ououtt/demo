package com.example.demo.constant.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-17 18:53
 **/
public enum SexEnum {

    MAN(1, "男"),
    WOMAN(2, "女");

    private int id;
    private String value;

    SexEnum(int id, String value) {
        this.id = id;
        this.value = value;
    }

    private static Map<Integer, SexEnum> idMap = Maps.newHashMap();

    static {
        for (SexEnum sexEnum : SexEnum.values()) {
            idMap.put(sexEnum.getId(), sexEnum);
        }
    }

    public static SexEnum parseId(int id) {
        return idMap.get(id);
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}

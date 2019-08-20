package com.bittrade.common.enums;

/**
 * @author xzc
 * @date 2019-08-19 18:12
 * @description 发送的类型
 */
public enum SendType {
    TEXT(0, "文本"),
    PHOTO(1, "照片");

    SendType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    private int code;

    private String name;
}

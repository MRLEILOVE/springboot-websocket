package com.bittrade.uac.model.enums;

import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author: xzc
 * @create: 2019/8/27 下午6:45
 * @description: 设备类型枚举
 **/
@Getter
public enum EquipmentTypeEnum {
    /**
     * 手机
     */
    SMS(1, "sms"),

    /**
     * 邮箱
     */
    EMAIL(2, "email");

    EquipmentTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    /**
     * 下标
     */
    private Integer code;
    /**
     * 字符
     */
    private String value;

}

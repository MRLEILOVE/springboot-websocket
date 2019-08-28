package com.bittrade.uac.model.enums;

import lombok.Getter;
import lombok.ToString;

/**
 * @author: xzc
 * @create: 2019-08-13 11:21
 * @description: 时间类型枚举
 **/
@Getter
@ToString
public enum TimeType {
    SECOND(0, "秒"),
    MINUTE(1, "分钟"),
    HOUR(2, "小时"),
    DAY(3, "天");

    private Integer code;
    private String value;

    TimeType(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public static TimeType getTimeType(int code) {
        switch (code) {
            case 0:
                return SECOND;
            case 1:
                return MINUTE;
            case 2:
                return HOUR;
            case 3:
            default:
                return DAY;
        }
    }


}

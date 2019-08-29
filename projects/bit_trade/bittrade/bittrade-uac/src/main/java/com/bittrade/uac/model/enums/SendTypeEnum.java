package com.bittrade.uac.model.enums;

import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author: xzc
 * @create: 2019/8/27 下午5:23
 * @description:
 **/
@Getter
public enum SendTypeEnum {
    /**
     * 用户注册
     */
    REGISTER(1, "register"),

    /**
     * 修改登陆密码
     */
    CHANGE_LOGIN_PWD(2, "changeLoginPwd"),

    /**
     * 查找密码
     */
    FIND_PWD(3, "findPwd"),

    /**
     * 修改二维码
     */
    CHANGE_RQ_CODE(4, "changeRqCode"),

    /**
     * 修改支付密码
     */
    CHANGE_PAY_PWD(5, "changePayPwd"),

    /**
     * 修改收付款信息
     */
    CHANGE_PAYMENT_INFORMATION(6, "changePaymentInformation");

    SendTypeEnum(Integer code, String value) {
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

    /**
     * 通过code获取枚举对象
     *
     * @param code
     * @return
     */
    public static SendTypeEnum getEnumByCode(Integer code) {
        if (Objects.isNull(code)) {
            return null;
        }
        SendTypeEnum[] values = SendTypeEnum.values();
        return Stream
                .of(values)
                .filter(value -> {
                    Integer enumCode = value.getCode();
                    return enumCode.intValue() == code.intValue();
                }).findFirst()
                .get();
    }
}

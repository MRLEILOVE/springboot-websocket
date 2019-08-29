package com.bittrade.uac.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


@Setter
@Getter
@ToString
public class UserRegisterVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 注册类型
     */
    private Integer registerType;

    /**
     * 手机区域码
     */
    private String areaCode;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

}

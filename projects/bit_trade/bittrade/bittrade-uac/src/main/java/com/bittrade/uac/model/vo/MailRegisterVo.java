package com.bittrade.uac.model.vo;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author: xzc
 * @create: 2019-08-14 11:33
 * @description: 邮箱注册需要的实体
 **/
@Getter
@Setter
@ToString
@NoArgsConstructor
public class MailRegisterVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱验证码
     */
    private String mailCode;
}

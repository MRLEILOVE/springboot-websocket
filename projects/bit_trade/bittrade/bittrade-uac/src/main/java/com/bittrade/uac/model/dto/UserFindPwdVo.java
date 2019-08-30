package com.bittrade.uac.model.dto;


import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * @author: xzc
 * @create: 2019-08-13 14:11
 * @description: 找回密码
 **/
@Setter
@Getter
@ToString
public class UserFindPwdVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 登陆名
     */
    private String loginName;


    /**
     * 新密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

}

package com.bittrade.uac.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: xzc
 * @create: 2019/8/29 下午3:23
 * @description:
 **/
@Getter
@Setter
@ToString
public class CurrentUserDto {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 登陆账号
     */
    private String loginName;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;
}

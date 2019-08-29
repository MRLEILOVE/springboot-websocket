package com.bittrade.uac.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author oublue
 * @since 2018-10-18
 */
@Data
public class UserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 登录名
     */
    private String loginName;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 电话号码
     */
    private String telePhone;
    /**
     * 推荐码
     */
    private String recommendCode;
    /**
     * 邮箱地址
     */
    private String userEmail;
    /**
     * 是否电话绑定:1绑定，0无效
     */
    private Integer isTelValidate;
    /**
     * 是否证件绑定:1绑定，0无效
     */
    private Integer isIdentityValidate;
    /**
     * 是否邮箱绑定:1绑定，0无效
     */
    private Integer isMailValidate;
    /**
     * 证件类型:证件类型:1.身份证 2.军官证，3.护照 4.台湾居民通行证 5.港澳居民通行证
     */
    private Integer identityType;
    /**
     * 证件号码
     */
    private String identityNo;
}
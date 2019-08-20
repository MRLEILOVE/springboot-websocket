package com.wallet.biz.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

@Data
public class UserFindPassSmsDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 手机号码
     */
    @NotBlank
    private String phone;


    /**
     * 新密码
     */
    @NotBlank
    private String newPass;

    /**
     * 手机验证码
     */
    @NotBlank
    private String phoneCode;

}

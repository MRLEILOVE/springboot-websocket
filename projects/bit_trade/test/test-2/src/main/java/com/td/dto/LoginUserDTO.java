package com.td.dto;

import lombok.Data;


/**
 * @description 登录用户传输参数
 * @author 
 * @date 
 */
@Data
public class LoginUserDTO {

    /**
     * 用户名
     */
    private String account;

    /**
     * 用户密码
     */
    private String password;

}

package com.td.dto;

import lombok.Data;

/**
 * @description 用户修改密码传输参数
 * @author 
 * @date 
 */
@Data
public class ModifyUserDTO {

    /**
     * 原密码
     */
    String oldPassword;

    /**
     * 新密码
     */
    String newPassword;
}

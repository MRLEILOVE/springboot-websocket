package com.bittrade.uac.model.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


/**
 * @author: xzc
 * @create: 2019/8/27 下午3:17
 * @description: 修改密码实体
 **/
@Getter
@Setter
@ToString
public class ChangePasswordDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 新密码
     */
    private String newPass;

    /**
     * 旧密码
     */
    private String oldPass;

}

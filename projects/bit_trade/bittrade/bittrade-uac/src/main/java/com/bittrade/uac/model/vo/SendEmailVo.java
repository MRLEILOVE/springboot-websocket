package com.bittrade.uac.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author: xzc
 * @create: 2019-08-14 15:26
 * @description:
 **/
@Getter
@Setter
@ToString
public class SendEmailVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 发送邮件类型
     */
    private Integer sendType;
}

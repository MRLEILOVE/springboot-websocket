package com.bittrade.uac.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: xzc
 * @create: 2019/8/28 下午6:39
 * @description:
 **/
@Getter
@Setter
@ToString
public class SendEmailDto {

    /**
     * 发送邮箱
     */
    private String email;

    /**
     * 发送内容
     */
    private String content;
}

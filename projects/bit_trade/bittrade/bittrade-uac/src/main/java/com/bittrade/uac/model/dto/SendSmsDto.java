package com.bittrade.uac.model.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author: xzc
 * @create: 2019/8/27 下午2:38
 * @description: 短信实体
 **/
@Getter
@Setter
@ToString
public class SendSmsDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 区域码
     */
    private String AreaCode;
    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 发送的内容
     */
    private String content;

}

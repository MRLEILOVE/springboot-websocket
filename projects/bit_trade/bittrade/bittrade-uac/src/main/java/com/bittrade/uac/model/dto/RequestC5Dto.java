package com.bittrade.uac.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: xzc
 * @create: 2019/8/27 下午7:03
 * @description:
 **/
@Getter
@Setter
@ToString
public class RequestC5Dto {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    @JsonProperty("password_md5")
    private String password;
    /**
     * 手机号
     */
    private String mobile;

    /**
     * 密匙
     */
    private String apiKey;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 编码
     */
    private String encode = "UTF-8";
}

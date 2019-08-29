package com.bittrade.uac.service.impl;

import com.bittrade.uac.model.dto.RequestC5Dto;
import com.bittrade.uac.model.dto.SendSmsDto;
import com.bittrade.uac.web.feign.client.SmsFeignClient;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * @author: xzc
 * @create: 2019-08-14 16:50
 * @description: 5c发送短信
 **/
@Slf4j
@Service
public class C5SmsService {

    /**
     * 当前环境
     */
    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    private C5SmsConfig c5SmsConfig;

    @Autowired
    private SmsFeignClient smsFeignClient;

    @Getter
    @Setter
    @Component
    @ConfigurationProperties(prefix = "sms.c5")
    public class C5SmsConfig {

        /**
         * 用户名
         */
        private String userName;

        /**
         * 密码
         */
        private String password;

        /**
         * url
         */
        private String url;

        /**
         * apiKey
         */
        private String apiKey;
    }

    public void send(SendSmsDto sendSmsDto) {
        String areaCode = sendSmsDto.getAreaCode();
        String phoneNumber = sendSmsDto.getPhoneNumber();
        String content = sendSmsDto.getContent();
        RequestC5Dto requestC5Dto = new RequestC5Dto();
        requestC5Dto.setUsername(c5SmsConfig.getUserName());
        requestC5Dto.setPassword(c5SmsConfig.getPassword());
        requestC5Dto.setMobile(areaCode + phoneNumber);
        requestC5Dto.setApiKey(c5SmsConfig.getApiKey());
        requestC5Dto.setContent(content);

        smsFeignClient.send(c5SmsConfig.getUserName(),
                c5SmsConfig.getPassword(),
                areaCode + phoneNumber,
                c5SmsConfig.getApiKey(),
                content,
                "UTF_8");
    }
}
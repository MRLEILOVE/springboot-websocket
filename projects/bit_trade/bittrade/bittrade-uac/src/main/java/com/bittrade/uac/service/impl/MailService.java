package com.bittrade.uac.service.impl;

import com.bittrade.uac.model.dto.SendEmailDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author: xzc
 * @create: 2019/8/28 下午5:06
 * @description: 参数
 **/
@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailProperty mailProperty;

    @Getter
    @Setter
    @Component
    @ConfigurationProperties(prefix = "spring.mail")
    public class MailProperty {
        /**
         * 发件人
         */
        private String username;

        /**
         * 标题
         */
        private String subject;
    }


    /**
     * 发送邮件
     *
     * @param mailDto
     */
    public void send(SendEmailDto mailDto) {
        String email = mailDto.getEmail();
        String content = mailDto.getContent();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mailProperty.getUsername());
        mailMessage.setTo(email);
        mailMessage.setSubject(mailProperty.getUsername());
        mailMessage.setText(content);
        mailSender.send(mailMessage);
    }
}

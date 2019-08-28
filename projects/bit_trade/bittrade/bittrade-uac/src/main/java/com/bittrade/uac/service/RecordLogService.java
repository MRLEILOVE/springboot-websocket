package com.bittrade.uac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bittrade.uac.model.dto.SendEmailDto;
import com.bittrade.uac.model.dto.SendSmsDto;
import com.bittrade.uac.model.enums.MessageTemplateEnum;
import com.bittrade.uac.model.pojo.RecordLog;

/**
 * @author: xzc
 * @create: 2019/8/27 下午6:35
 * @description:
 **/
public interface RecordLogService extends IService<RecordLog> {

    /**
     * 保存信息发送日志
     *
     * @param sendSmsDto
     * @param msTemplate 消息模版
     */
    void save(SendSmsDto sendSmsDto, MessageTemplateEnum msTemplate);

    /**
     * 保存邮件发送日志
     * @param emailDto
     * @param msTemplate
     */
    void save(SendEmailDto emailDto, MessageTemplateEnum msTemplate);
}

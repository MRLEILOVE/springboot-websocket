package com.bittrade.uac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bittrade.uac.mapper.RecordLogMapper;
import com.bittrade.uac.model.dto.SendEmailDto;
import com.bittrade.uac.model.dto.SendSmsDto;
import com.bittrade.uac.model.enums.EquipmentTypeEnum;
import com.bittrade.uac.model.enums.MessageTemplateEnum;
import com.bittrade.uac.model.pojo.RecordLog;
import com.bittrade.uac.service.RecordLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: xzc
 * @create: 2019/8/27 下午6:35
 * @description:
 **/
@Service
public class RecordLogServiceImpl extends ServiceImpl<RecordLogMapper, RecordLog> implements RecordLogService {

    @Autowired
    private RecordLogService recordLogService;

    @Override
    public void save(SendSmsDto sendSmsDto, MessageTemplateEnum msTemplate) {
        String content = sendSmsDto.getContent();
        String phoneNumber = sendSmsDto.getPhoneNumber();
        String areaCode = sendSmsDto.getAreaCode();
        RecordLog recordLog = new RecordLog();
        recordLog.setEquipmentType(EquipmentTypeEnum.SMS.getCode());
        recordLog.setBusinessType(msTemplate.getBusType());
        recordLog.setEquipmentMark(areaCode + phoneNumber);
        recordLog.setContent(content);
        recordLogService.save(recordLog);
    }

    @Override
    public void save(SendEmailDto emailDto, MessageTemplateEnum msTemplate) {
        String email = emailDto.getEmail();
        String content = emailDto.getContent();
        RecordLog recordLog = new RecordLog();
        recordLog.setEquipmentType(EquipmentTypeEnum.SMS.getCode());
        recordLog.setBusinessType(msTemplate.getBusType());
        recordLog.setEquipmentMark(email);
        recordLog.setContent(content);
        recordLogService.save(recordLog);
    }
}

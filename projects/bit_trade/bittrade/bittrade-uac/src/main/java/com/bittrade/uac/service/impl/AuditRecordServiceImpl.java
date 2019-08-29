package com.bittrade.uac.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bittrade.uac.mapper.AuditRecordMapper;
import com.bittrade.uac.model.pojo.AuditRecord;
import com.bittrade.uac.service.AuditRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: xzc
 * @create: 2019/8/27 下午4:43
 * @description: 审核记录
 **/
@Service
public class AuditRecordServiceImpl extends ServiceImpl<AuditRecordMapper, AuditRecord> implements AuditRecordService {

    @Autowired
    private AuditRecordService auditRecordService;

    @Override
    public AuditRecord getByUserId(Long userId) {
        AuditRecord auditRecord = new AuditRecord();
        auditRecord.setUserId(userId);
        Wrapper<AuditRecord> wrapper = Wrappers.lambdaQuery(auditRecord);
        return auditRecordService.getOne(wrapper);
    }
}


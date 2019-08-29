package com.bittrade.uac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bittrade.uac.model.pojo.AuditRecord;

/**
 * @author: xzc
 * @create: 2019/8/27 下午2:38
 * @description: 审核记录
 **/
public interface AuditRecordService extends IService<AuditRecord> {

    /**
     * 通过userId获取审核记录
     *
     * @param userId
     * @return
     */
    AuditRecord getByUserId(Long userId);
}

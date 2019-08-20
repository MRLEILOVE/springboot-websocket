package com.walletbiz.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.walletbiz.mapper.PersonalAccountRecordMapper;
import com.walletbiz.pojo.CPersonalAccountRecord;
import com.walletbiz.service.PersonalAccountRecordService;

/**
 * <p>
 * 我的资产流水表 服务实现类
 * </p>
 *
 * @author helen
 * @since 2019-03-05
 */
@Service
public class PersonalAccountRecordServiceImpl extends ServiceImpl<PersonalAccountRecordMapper, CPersonalAccountRecord> implements PersonalAccountRecordService {

}

package com.wallet.biz.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wallet.biz.mapper.PersonalAccountRecordMapper;
import com.wallet.biz.pojo.CPersonalAccountRecord;
import com.wallet.biz.service.PersonalAccountRecordService;

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

package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.provider.dto.AccountRecordDto;
import com.jdcloud.provider.mapper.PersonalAccountRecordMapper;
import com.jdcloud.provider.pojo.PersonalAccountRecord;
import com.jdcloud.provider.pojo.vo.PersonalAccountRecordVo;
import com.jdcloud.provider.service.PersonalAccountRecordService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 我的资产流水表 服务实现类
 * </p>
 *
 * @author helne
 * @since 2019-03-22
 */
@Service
public class PersonalAccountRecordServiceImpl extends ServiceImpl<PersonalAccountRecordMapper, PersonalAccountRecord> implements PersonalAccountRecordService {
    @Override
    public Page<PersonalAccountRecordVo> getAccountList(Page<PersonalAccountRecordVo> page, AccountRecordDto accountRecordDto) {
        page.setRecords(baseMapper.getAccountList(page,accountRecordDto));
        return page;
    }
}

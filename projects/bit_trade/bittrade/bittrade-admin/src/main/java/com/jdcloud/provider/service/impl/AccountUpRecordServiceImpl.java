package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.AccountUpRecordDto;
import com.jdcloud.provider.dto.BetaAccountDto;
import com.jdcloud.provider.pojo.AccountUpRecord;
import com.jdcloud.provider.mapper.AccountUpRecordMapper;
import com.jdcloud.provider.pojo.vo.AccountUpRecordVo;
import com.jdcloud.provider.service.AccountUpRecordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 我的资产流水表 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-05-29
 */
@Service
public class AccountUpRecordServiceImpl extends ServiceImpl<AccountUpRecordMapper, AccountUpRecord> implements AccountUpRecordService {

    @Override
    public Page<AccountUpRecordVo> selectAccountUpRecordList(Page<AccountUpRecordVo> page, AccountUpRecordDto dto) {
        page.setRecords(baseMapper.selectAccountUpRecordList(page, dto));
        return page;
    }
}

package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.provider.dto.BetaAccountRecordDto;
import com.jdcloud.provider.mapper.BetaAccountRecordMapper;
import com.jdcloud.provider.pojo.BetaAccountRecord;
import com.jdcloud.provider.pojo.vo.BetaAccountRecordVo;
import com.jdcloud.provider.service.BetaAccountRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 我的beta资产流水表 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-04-24
 */
@Service
public class BetaAccountRecordServiceImpl extends ServiceImpl<BetaAccountRecordMapper, BetaAccountRecord> implements BetaAccountRecordService {

    @Resource
    private  BetaAccountRecordMapper betaAccountRecordMapper;


    @Override
    public Page<BetaAccountRecordVo> betaAccountRecord(Page<BetaAccountRecordVo> page,Integer id, BetaAccountRecordDto dto) {
        page.setRecords(betaAccountRecordMapper.betaAccountRecord(page,id, dto));
        return page;
    }
}

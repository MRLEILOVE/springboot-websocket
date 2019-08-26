package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.BetaFreezeAccountDto;
import com.jdcloud.provider.pojo.BetaFreezeAccountRecord;
import com.jdcloud.provider.mapper.BetaFreezeAccountRecordMapper;
import com.jdcloud.provider.pojo.vo.BetaFreezeAccountRecordVo;
import com.jdcloud.provider.service.BetaFreezeAccountRecordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-08-08
 */
@Service
public class BetaFreezeAccountRecordServiceImpl extends ServiceImpl<BetaFreezeAccountRecordMapper, BetaFreezeAccountRecord> implements BetaFreezeAccountRecordService {

    /**
     * 冻结资金流水
     * @param page
     * @param dto
     * @return
     */
    @Override
    public Page<BetaFreezeAccountRecordVo> getBetaAccountRecord(Page<BetaFreezeAccountRecordVo> page, BetaFreezeAccountDto dto) {
        page.setRecords(baseMapper.getBetaAccountRecord(page,dto));
        return page;
    }
}

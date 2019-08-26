package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.WalletFundAccountDto;
import com.jdcloud.provider.pojo.WalletFundAccountRecord;
import com.jdcloud.provider.mapper.WalletFundAccountRecordMapper;
import com.jdcloud.provider.pojo.vo.WalletFundAccountRecordVo;
import com.jdcloud.provider.service.WalletFundAccountRecordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资金账户流水表 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-07-23
 */
@Service
public class WalletFundAccountRecordServiceImpl extends ServiceImpl<WalletFundAccountRecordMapper, WalletFundAccountRecord> implements WalletFundAccountRecordService {

    /**
     * 查询流水详情
     * @param page
     * @param dto
     * @return
     */
    @Override
    public Page<WalletFundAccountRecordVo> getWalletFundAccountRecord(Page<WalletFundAccountRecordVo> page, WalletFundAccountDto dto) {
        return page.setRecords(baseMapper.getWalletFundAccountRecord(page,dto));
    }
}

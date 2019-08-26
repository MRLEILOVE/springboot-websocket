package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.WalletFundAccountDto;
import com.jdcloud.provider.pojo.WalletFundAccountRecord;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.pojo.vo.WalletFundAccountRecordVo;

/**
 * <p>
 * 资金账户流水表 服务类
 * </p>
 *
 * @author C
 * @since 2019-07-23
 */
public interface WalletFundAccountRecordService extends IService<WalletFundAccountRecord> {

    Page<WalletFundAccountRecordVo> getWalletFundAccountRecord(Page<WalletFundAccountRecordVo> page, WalletFundAccountDto dto);
}

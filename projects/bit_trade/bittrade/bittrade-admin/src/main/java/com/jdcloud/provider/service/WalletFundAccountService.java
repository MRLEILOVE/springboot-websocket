package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.WalletFundAccountDto;
import com.jdcloud.provider.pojo.WalletFundAccount;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.pojo.vo.WalletFundAccountVo;

/**
 * <p>
 * 资金账户表 服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-07-23
 */
public interface WalletFundAccountService extends IService<WalletFundAccount> {

    Page<WalletFundAccountVo> getWithdrawWalletBill(Page<WalletFundAccountVo> page, WalletFundAccountDto dto);
}

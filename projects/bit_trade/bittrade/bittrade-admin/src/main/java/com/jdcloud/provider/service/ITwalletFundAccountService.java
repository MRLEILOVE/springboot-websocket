package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.TWalletFundAccountDto;
import com.jdcloud.provider.dto.WalletFundDto;
import com.jdcloud.provider.pojo.TWalletFundAccount;
import com.jdcloud.provider.pojo.vo.TWalletFundAccountVo;

import java.util.List;


public interface ITwalletFundAccountService extends IService<TWalletFundAccount> {

    List<TWalletFundAccount> selectByParam(WalletFundDto walletFundDto);

    Page<TWalletFundAccountVo> getTWalletFundAccount(Page<TWalletFundAccountVo> page, TWalletFundAccountDto tWalletFundAccountDto);
}

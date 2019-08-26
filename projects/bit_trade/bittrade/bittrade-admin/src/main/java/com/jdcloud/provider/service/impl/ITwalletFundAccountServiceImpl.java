package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.provider.dto.TWalletFundAccountDto;
import com.jdcloud.provider.dto.WalletFundDto;
import com.jdcloud.provider.mapper.TwalletFundAccountMapper;
import com.jdcloud.provider.pojo.TWalletFundAccount;
import com.jdcloud.provider.pojo.vo.TWalletFundAccountVo;
import com.jdcloud.provider.service.ITwalletFundAccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ITwalletFundAccountServiceImpl extends ServiceImpl<TwalletFundAccountMapper, TWalletFundAccount> implements ITwalletFundAccountService {


    @Override
    public List<TWalletFundAccount> selectByParam(WalletFundDto walletFundDto) {
        return null;
    }

    /**
     * 获取到资金列表
     *
     * @param page
     * @param tWalletFundAccountDto
     * @return
     */
    @Override
    public Page<TWalletFundAccountVo> getTWalletFundAccount(Page<TWalletFundAccountVo> page, TWalletFundAccountDto tWalletFundAccountDto) {
        return page.setRecords(baseMapper.getTWalletFundAccount(page, tWalletFundAccountDto));
    }
}

package com.wallet.biz.feign.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.wallet.biz.dto.TransferDto;
import com.wallet.biz.feign.ITransferFeignService;

@Component
public class TransferFeignServiceImpl implements ITransferFeignService {
    /**
     * 币币账户入账的熔断方法
     */
    @Override
    public String biBiAccountEntry(TransferDto transferDto) {
        return "timeOut";
    }

    /**
     * 币币账户出账的熔断方法
     */
    @Override
    public String biBiAccountOut(TransferDto transferDto) {
        return "timeOut";
    }

    /**
     * 获取所有可用币种
     */
    @Override
    public List<String> findUsableCurrency() {
        return null;
    }

    /**
     * 获取用户币币账户的钱包余额
     * @param userId 用户id
     * @param currency 币种
     * @return 钱包余额
     */
    @Override
    public String availableBalanceFeign(Long userId,String currency) {
        return "0";
    }
}

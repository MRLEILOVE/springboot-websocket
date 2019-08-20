package com.wallet.biz.feign.impl;

import org.springframework.stereotype.Component;

import com.wallet.biz.feign.IC2CTransferFeignService;

@Component
public class C2CTransferFeignServiceImpl implements IC2CTransferFeignService {
    @Override
    public String availableBalanceFeign(Long userId, String coinName) {
        return "0";
    }
}

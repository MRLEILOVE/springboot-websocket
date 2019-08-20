package com.walletbiz.feign.impl;

import org.springframework.stereotype.Component;

import com.walletbiz.feign.IC2CTransferFeignService;

@Component
public class C2CTransferFeignServiceImpl implements IC2CTransferFeignService {
    @Override
    public String availableBalanceFeign(Long userId, String coinName) {
        return "0";
    }
}

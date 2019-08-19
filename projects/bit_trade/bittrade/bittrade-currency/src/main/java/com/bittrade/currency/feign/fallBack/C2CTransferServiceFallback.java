package com.bittrade.currency.feign.fallBack;

import com.bittrade.currency.feign.IC2CTransferService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class C2CTransferServiceFallback implements IC2CTransferService {
    @Override
    public BigDecimal getAssetsFeign(Long userId) {
        return BigDecimal.ZERO;
    }
}

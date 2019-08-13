package com.bittrade.currency.feign;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class TransferFallBack implements ITransferFeignService {
    @Override
    public String c2cAccountEntry(Long userId, String currency, BigDecimal num) {
        return "timeOut";
    }
}

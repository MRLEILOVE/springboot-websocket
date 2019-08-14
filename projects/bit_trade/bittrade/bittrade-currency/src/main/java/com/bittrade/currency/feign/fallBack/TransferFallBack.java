package com.bittrade.currency.feign.fallBack;

import com.bittrade.currency.feign.ITransferFeignService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class TransferFallBack implements ITransferFeignService {
    @Override
    public String c2cAccountEntry(Long userId, String currency, BigDecimal num) {
        return "timeOut";
    }
}

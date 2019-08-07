package com.bittrade.currency.feign;

import com.core.web.common.config.feign.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;


@FeignClient(name = "jdcloud-provider-walletbiz",path = "/transfer",configuration = FeignConfiguration.class,fallback = TransferFallBack.class)
public interface ITransferFeignService {

    @GetMapping(value = "/c2cAccountEntry")
    String c2cAccountEntry(@RequestParam("userId")Long userId, @RequestParam("currency")String currency,@RequestParam("num") BigDecimal num);
}

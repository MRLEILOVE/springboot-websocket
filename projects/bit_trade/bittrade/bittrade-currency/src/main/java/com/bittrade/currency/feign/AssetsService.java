package com.bittrade.currency.feign;

import com.bittrade.currency.feign.fallBack.TransferFallBack;
import com.core.web.common.config.feign.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "jdcloud-provider-walletbiz",path = "/assets",configuration = FeignConfiguration.class,fallback = TransferFallBack.class)
public interface AssetsService {
    @GetMapping(value = "/getAssets")
    String getAssets(@RequestParam("userId")Long userId);
}

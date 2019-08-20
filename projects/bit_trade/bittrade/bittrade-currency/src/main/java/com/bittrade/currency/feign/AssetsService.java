package com.bittrade.currency.feign;

import com.bittrade.currency.feign.fallBack.AssetsFallBack;
import com.bittrade.currency.feign.fallBack.TransferFallBack;
import com.bittrade.pojo.vo.ConversionVo;
import com.core.web.common.config.feign.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "jdcloud-provider-walletbiz",configuration = FeignConfiguration.class,fallback = AssetsFallBack.class)
public interface AssetsService {

    @GetMapping(value = "/assets/getAssets")
    String getAssets(@RequestParam("userId")Long userId);


}

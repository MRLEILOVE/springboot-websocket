package com.bittrade.currency.feign;

import com.bittrade.currency.feign.fallBack.TransferFallBack;
import com.bittrade.pojo.vo.ConversionVo;
import com.core.web.common.config.feign.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "jdcloud-provider-walletbiz",configuration = FeignConfiguration.class,fallback = TransferFallBack.class)
public interface AssetsService {

    @GetMapping(value = "/assets/getAssets")
    String getAssets(@RequestParam("userId")Long userId);

    /**
     * 远程调用资金账户总资产折合
     * @param userId 用户
     * @return
     */
    @PostMapping(value = "/wallet/conversionTotal")
    ConversionVo personalTotalConversion(Long userId);

    /**
     * 远程调用法币账户总资产折合
     * @param userId 用户id
     */
//    ConversionVo personalTotalConversion(Long userId);
}

package com.bittrade.currency.feign;

import com.bittrade.currency.feign.fallBack.C2CTransferServiceFallback;
import com.core.web.common.config.feign.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "bittrade-c2c",configuration = FeignConfiguration.class,fallback = C2CTransferServiceFallback.class)
public interface IC2CTransferService {

    /**
     * 获取法币账户的总资金折合
     * @param userId 用户id
     * @return
     */
    @GetMapping("/tLegalCurrencyAccount/getAssetsFeign")
    BigDecimal getAssetsFeign(@RequestParam("userId") Long userId);
}

package com.bittrade.currency.feign;

import com.bittrade.currency.feign.fallBack.TransferFallBack;
import com.core.web.common.config.feign.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;


@FeignClient(name = "jdcloud-provider-walletbiz",path = "/transfer",configuration = FeignConfiguration.class,fallback = TransferFallBack.class)
public interface ITransferFeignService {

    @GetMapping(value = "/accountEntry")
    String accountEntry(@RequestParam("userId")Long userId, @RequestParam("currency")String currency,@RequestParam("num") BigDecimal num,@RequestParam("type") Integer type);

    /**
     * 获取划转类型
     * @param accountInId 入账钱包id
     * @param accountOutId 出账钱包id
     * @return
     */
    @GetMapping(value = "/getTypeFeign")
    Integer getTypeFeign(@RequestParam("accountInId")Integer accountInId, @RequestParam("accountOutId")Integer accountOutId);
}

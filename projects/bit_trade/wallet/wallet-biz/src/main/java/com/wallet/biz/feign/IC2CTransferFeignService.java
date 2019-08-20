package com.wallet.biz.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wallet.biz.feign.impl.C2CTransferFeignServiceImpl;

//@FeignClient(name = "bittrade-c2c",configuration = OAuth2FeignAutoConfiguration.class,fallback = C2CTransferFeignServiceImpl.class)
@FeignClient(name = "bittrade-c2c",fallback = C2CTransferFeignServiceImpl.class)
public interface IC2CTransferFeignService {
    /**
     * 获取用户c2c账户可用的钱包余额
     * @param userId 用户id
     * @param coinName 币种
     * @return 钱包余额
     */
    @GetMapping(value = "/tLegalCurrencyAccount/availableBalanceFeign")
    String availableBalanceFeign(@RequestParam("userId") Long userId, @RequestParam("coinName") String coinName);
}

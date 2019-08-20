package com.wallet.biz.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.wallet.biz.dto.TransferDto;
import com.wallet.biz.feign.impl.TransferFeignServiceImpl;

//@FeignClient(name = "bittrade-currency",configuration = OAuth2FeignAutoConfiguration.class,fallback = TransferFeignServiceImpl.class)
@FeignClient(name = "bittrade-currency",fallback = TransferFeignServiceImpl.class)
public interface ITransferFeignService {
    /**
     * 币币账户入账
     */
    @PostMapping(value = "/tWalletTransfer/biBiAccountEntry")
    String biBiAccountEntry(@RequestBody TransferDto transferDto);

    /**
     * 币币账户出账
     */
    @PostMapping(value = "/tWalletTransfer/biBiAccountOut")
    String biBiAccountOut(TransferDto transferDto);

    /**
     * 获取所有可用币种
     */
    @PostMapping(value = "/tCurrency/findUsableCurrency")
    List<String> findUsableCurrency();

    /**
     * 获取用户币币账户的钱包余额
     * @param userId 用户id
     * @param currency 币种
     * @return 钱包余额
     */
    @GetMapping(value = "/tWalletTransfer/availableBalanceFeign")
    String availableBalanceFeign(@RequestParam("userId") Long userId, @RequestParam("currency")String currency);
}

package com.bittrade.currency.feign;

import com.bittrade.pojo.dto.TransferDto;
import com.core.web.common.config.feign.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "jdcloud-provider-walletbiz",path = "/transfer",configuration = FeignConfiguration.class,fallback = TransferFallBack.class)
public interface ITransferFeignService {
    @RequestMapping(value = "/print",method = RequestMethod.POST)
    String print(String a);

    @RequestMapping(value = "/c2cAccountEntry",method= RequestMethod.POST)
    String c2cAccountEntry( TransferDto transferDto);
}

package com.bittrade.currency.feign;

import com.bittrade.pojo.dto.TransferDto;
import com.core.common.DTO.ReturnDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;

@FeignClient(name = "jdcloud-provider-walletbiz",path = "/fundAcoount",fallback = TransferFallBack.class)
public interface ITransferFeignService {
    @PostMapping("/print")
    String print(String ss);

    @PostMapping("/c2cAccountEntry")
    ReturnDTO<String> c2cAccountEntry(TransferDto transferDto);
}

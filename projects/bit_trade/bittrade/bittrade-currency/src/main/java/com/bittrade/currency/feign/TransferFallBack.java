package com.bittrade.currency.feign;

import com.bittrade.pojo.dto.TransferDto;
import com.core.common.DTO.ReturnDTO;
import org.springframework.stereotype.Component;


@Component
public class TransferFallBack implements ITransferFeignService {


    @Override
    public String print(String ss) {
        System.out.println("进入熔断处理");
        return new String();
    }

    @Override
    public ReturnDTO<String> c2cAccountEntry(TransferDto transferDto) {
        System.out.println("1111111111111111111111");
        return ReturnDTO.ok("ok");
    }
}

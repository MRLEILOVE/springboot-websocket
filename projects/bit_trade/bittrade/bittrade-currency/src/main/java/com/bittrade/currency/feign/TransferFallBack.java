package com.bittrade.currency.feign;

import com.bittrade.pojo.dto.TransferDto;
import com.core.common.DTO.ReturnDTO;
import org.springframework.stereotype.Component;


@Component
public class TransferFallBack implements ITransferFeignService {


    @Override
    public String print(String a) {
        return new String();
    }

    @Override
    public String c2cAccountEntry(TransferDto transferDto) {
        return new String();
    }
}

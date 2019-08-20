package com.walletbiz.dto;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class WalletDto extends PageDto  {


    @ApiParam(value = "币种传 USDT 或者 BTC" ,required =false,example="USDT")
    private String token;
    @ApiParam(value = "记录类型，recharge或者withdraw" ,required = false,example="recharge")
    private String type;

}

package com.walletbiz.vo;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CoinTypeDto {


    @ApiParam(value = "币种传 USDT 或者 BTC" ,required = true,example="USDT")
    @NotNull(message = "币种不能为空")
    private String token;

    @ApiParam(value = "二维码地址" ,required = false,example="/test/a.jpg")
    private String url;



}

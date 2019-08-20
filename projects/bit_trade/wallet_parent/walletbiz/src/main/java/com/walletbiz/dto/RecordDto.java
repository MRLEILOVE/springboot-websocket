package com.walletbiz.dto;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class RecordDto {
    @ApiParam(value = "用户id" ,required = true,example="5")
    private Long userId;
    @ApiParam(value = "币种" ,required = true,example="BTC")
    private String currency;
    @ApiParam(value = "当前页" ,required = true,example="1")
    private Integer current;
    @ApiParam(value = "页大小" ,required = true,example="5")
    private Integer size;
}

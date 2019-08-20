package com.walletbiz.dto;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddressDto {
    @ApiParam(value = "地址id,更新时必填" ,required = false,example="1")
    private Integer id;

    @ApiParam(value = "币种类型" ,required = true,example="BTC,USDT")
    @NotNull(message = "币种不能为空")
    private String tokenType;

    @ApiParam(value = "地址名称" ,required = false,example="老王的ETH地址")
    private String name;

    @ApiParam(value = "地址" ,required = true,example="mn7ihoauzVFZTLWKwQzkWBLBdnyriC9pv4")
    @NotNull(message = "地址不能为空")
    private String address;
}

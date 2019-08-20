package com.wallet.biz.dto;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class JudgmentDto {
    @NotBlank(message = "具体币种不能为空")
    @ApiParam(value = "具体币种" ,required = true,example="USDT,BTC")
    private String token;   //具体币种

    @ApiParam(value = "提币数量" ,required = false,example="0.000012")
    private BigDecimal amount; //提币数量

    @ApiParam(value = "接收地址" ,required = false,example="mn7ihoauzVFZTLWKwQzkWBLBdnyriC9pv4")
    private String receiverAddress;//接收地址

    //手续费
    @ApiParam(value = "手续费" ,required = false,example="0.0005")
    private BigDecimal free;


}


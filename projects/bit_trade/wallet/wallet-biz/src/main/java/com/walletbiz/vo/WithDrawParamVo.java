package com.walletbiz.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;


import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WithDrawParamVo {
    @NotNull(message = "具体币种不能为空")
    @ApiParam(value = "具体币种" ,required = true,example="USDT,BTC")
    private String token;   //具体币种

    @ApiParam(value = "提币数量" ,required = true,example="0.000012")
    @NotNull(message = "提币数量不能为空")
    private String amount; //提币数量


    @ApiParam(value = "接收地址" ,required = true,example="mn7ihoauzVFZTLWKwQzkWBLBdnyriC9pv4")
    @NotBlank(message = "接收地址不能为空")
    private String receiverAddress;//接收地址
    //手续费
    @NotNull(message = "手续费默认不能为空")
    @ApiParam(value = "接收地址" ,required = true,example="0.0005")
    @ApiModelProperty("比特币的手续费0.0005-0.001，USDT网络手续费4~10个")
    private String free;

    @NotNull(message = "手续费默认不能为空")
    @ApiParam(value = "支付密码" ,required = true,example="123456")
    private String password;


}

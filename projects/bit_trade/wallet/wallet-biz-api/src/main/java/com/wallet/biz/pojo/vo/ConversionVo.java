package com.wallet.biz.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConversionVo {
    @ApiModelProperty("账户名称")
    private String account;
    @ApiModelProperty("usdt数量")
    private BigDecimal USDT;
    @ApiModelProperty("人民币数量")
    private BigDecimal CNY;
}

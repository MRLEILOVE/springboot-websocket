package com.walletbiz.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BetaTotal {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("币种")
    private String currency;

    @ApiModelProperty("余额")
    private BigDecimal balance;

    @ApiModelProperty("冻结金额")
    private BigDecimal usedMargin;

}

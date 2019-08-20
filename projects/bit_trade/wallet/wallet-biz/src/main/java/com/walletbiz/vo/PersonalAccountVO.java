package com.walletbiz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("法币钱包账户对象")
public class PersonalAccountVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("币种")
    private String currency;

    @ApiModelProperty("账户可用余额")
    private BigDecimal balance;

    @ApiModelProperty("冻结资产")
    private BigDecimal usedMargin;
}

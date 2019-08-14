package com.bittrade.pojo.vo;

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
public class AccountVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("币种")
    private String currency;

    @ApiModelProperty("可用总金额")
    private BigDecimal balance;

    @ApiModelProperty("冻结总金额")
    private BigDecimal usedMargin;
}

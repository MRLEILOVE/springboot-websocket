package com.bittrade.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "交易对列表")
public class TransactionPairVO {
    @ApiModelProperty(value = "交易对id")
    private Integer id;

    @ApiModelProperty(value = "交易对,如BTC/USDT")
    private String symbol;

    @ApiModelProperty(value = "货币id，t_currency表中的id")
    private Integer currencyId1;

    @ApiModelProperty(value = "法币id，t_currency表中的id")
    private Integer currencyId2;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "涨跌幅")
    private BigDecimal chg;

    @ApiModelProperty(value = "自选id(根据有无id没判断是否加入自选)")
    private Integer optionalId;
}

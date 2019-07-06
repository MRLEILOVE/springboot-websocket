package com.bittrade.pojo.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
//@ApiModel(description = "交易对列表")
public class TransactionPairVO {
//    @ApiModelProperty(value = "id")
    private int id;

//    @ApiModelProperty(value = "交易对,如BTC/USDT")
    private String symbol;

//    @ApiModelProperty(value = "货币id，t_currency表中的id")
    private int currencyId1;

//    @ApiModelProperty(value = "法币id，t_currency表中的id")
    private int currencyId2;

//    @ApiModelProperty(value = "价格")
    private Double price;

//    @ApiModelProperty(value = "涨跌幅")
    private Double chg;
}

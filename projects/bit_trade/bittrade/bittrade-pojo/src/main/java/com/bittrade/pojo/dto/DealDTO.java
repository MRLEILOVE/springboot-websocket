package com.bittrade.pojo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
//@ApiModel(description = "接收买/卖参数")
public class DealDTO implements Serializable {

//    @ApiModelProperty(value = "用户id")
    private Long userId;

//    @ApiModelProperty(value = "交易对id")
    private Long currencyTradeId;

//    @ApiModelProperty(value = "单价")
    private Double price;

//    @ApiModelProperty(value = "总价")
    private Double amount;

//    @ApiModelProperty(value = "数量")
    private Double count;

//    @ApiModelProperty(value = "委托类型:0市价交易,1限价交易")
    private Integer entrustType;

//    @ApiModelProperty(value = "委托方向:0买入,1卖出")
    private Integer entrustDirection;
}

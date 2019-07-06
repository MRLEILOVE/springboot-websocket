package com.bittrade.pojo.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
//@ApiModel(description = "委托单信息")
public class TEntrustInfoVO {

//    @ApiModelProperty(value = "委托时间")
    private Date entrustTime;

//    @ApiModelProperty(value = "币种")
    private String currencyName;

//    @ApiModelProperty(value = "委托总量")
    private Double count;

//    @ApiModelProperty(value = "未完成数量")
    private Double leftCount;

//    @ApiModelProperty(value = "状态:1未完成,2部分成交,3完全成交,4用户撤销")
    private Integer status;

//    @ApiModelProperty(value = "交易方式:0买入,1卖出")
    private Integer entrustDirection;

//    @ApiModelProperty(value = "委托价")
    private Double amount;

//    @ApiModelProperty(value = "平均价")
    private Double price;

}

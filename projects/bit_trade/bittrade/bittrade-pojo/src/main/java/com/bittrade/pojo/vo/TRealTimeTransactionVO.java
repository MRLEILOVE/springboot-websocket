package com.bittrade.pojo.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
//@ApiModel(description = "实时成交VO")
public class TRealTimeTransactionVO {
//    @ApiModelProperty(value = "时间")
    private Date time;

//    @ApiModelProperty(value = "价格")
    private Double price;

//    @ApiModelProperty(value = "数量")
    private Long count;
}

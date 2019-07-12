package com.bittrade.pojo.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * 委托单信息
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TEntrustInfoVO {

    /**
     * 委托时间
     */
    private Date entrustTime;

    /**
     * 币种
     */
    private String currencyName;

    /**
     * 委托总量
     */
    private Double count;

    /**
     * 未完成数量
     */
    private Double leftCount;

    /**
     * 状态:1未完成,2部分成交,3完全成交,4用户撤销
     */
    private Integer status;

    /**
     * 交易方式:0买入,1卖出
     */
    private Integer entrustDirection;

    /**
     * 委托价
     */
    private Double amount;

    /**
     * 平均价
     */
    private Double price;

}

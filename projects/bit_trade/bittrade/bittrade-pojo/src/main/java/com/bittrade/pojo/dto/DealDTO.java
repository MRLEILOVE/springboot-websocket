package com.bittrade.pojo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 接收买/卖参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DealDTO implements Serializable {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 交易对id
     */
    private Long currencyTradeId;

    /**
     * 单价
     */
    private String price;

    /**
     * 总价
     */
    private String amount;

    /**
     * 数量
     */
    private String count;

    /**
     * 委托类型:0市价交易,1限价交易
     */
    private Integer entrustType;

    /**
     * 委托方向:0买入,1卖出
     */
    private Integer entrustDirection;
}

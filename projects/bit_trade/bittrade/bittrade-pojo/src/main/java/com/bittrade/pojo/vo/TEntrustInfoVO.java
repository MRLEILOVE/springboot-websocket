package com.bittrade.pojo.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 委托单信息
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TEntrustInfoVO implements Serializable {

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
    private BigDecimal count;

    /**
     * 未完成数量
     */
    private BigDecimal leftCount;

    /**
     * 未完成数量
     */
    private BigDecimal completedCount;

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
    private BigDecimal amount;

    /**
     * 平均价
     */
    private BigDecimal price;

    /**
     * 委托类型
     */
    private Integer entrustType;

}

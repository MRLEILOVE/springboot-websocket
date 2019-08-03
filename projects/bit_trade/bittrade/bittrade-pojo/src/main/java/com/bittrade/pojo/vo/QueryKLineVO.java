package com.bittrade.pojo.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryKLineVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 交易对
     */
    private String symbol;

    /**
     * 最高价格
     */
    private BigDecimal high;

    /**
     * 最低价格
     */
    private BigDecimal low;

    /**
     * 开盘价格
     */
    private BigDecimal open;

    /**
     * 收盘价格
     */
    private BigDecimal close;

    /**
     * 时间粒度[1min,5min,15min,30min,60min,4hour,1day,1mon,1week,1year]
     */
    private BigDecimal granularity;

    /**
     * 开始时间
     */
    private Date time;

    /**
     * 交易量
     */
    private BigDecimal volume;

    /**
     * 当前价
     */
    private BigDecimal price;

    /**
     * 涨跌幅
     */
    private BigDecimal chg;

}

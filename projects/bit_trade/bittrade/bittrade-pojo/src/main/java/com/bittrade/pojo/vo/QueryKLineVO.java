package com.bittrade.pojo.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryKLineVO {
    /**
     * 交易对
     */
    private String symbol;

    /**
     * 最高价格
     */
    private Double high;

    /**
     * 最低价格
     */
    private Double low;

    /**
     * 开盘价格
     */
    private Double open;

    /**
     * 收盘价格
     */
    private Double close;

    /**
     * 时间粒度[1min,5min,15min,30min,60min,4hour,1day,1mon,1week,1year]
     */
    private Double granularity;

    /**
     * 开始时间
     */
    private Date time;

    /**
     * 交易量
     */
    private Double volume;

}

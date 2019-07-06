package com.bittrade.pojo.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryKLineDto {

    /**
     * 开始时间
     */
    private String beginTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 粒度（时间粒度[1min,5min,15min,30min,60min,4hour,1day,1mon,1week,1year]）
     */
    private String granularity;

    /**
     * 交易对(名字)
     */
    private String symbol;


}

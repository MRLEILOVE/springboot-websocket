package com.bittrade.pojo.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryKLineVO {
    private String symbol;
    private Double high;
    private Double low;
    private Double open;
    private Double close;
    private Double granularity;
    private Date time;
    private Double volume;

}

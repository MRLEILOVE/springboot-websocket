package com.bittrade.pojo.vo;

import com.core.framework.base.VO.BaseVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TKlineVO extends BaseVO<TKlineVO> {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String symbol;
	private java.math.BigDecimal low;
	private java.math.BigDecimal high;
	private java.math.BigDecimal open;
	private java.math.BigDecimal close;
	private java.math.BigDecimal volume;
	private String granularity;
	private java.util.Date time;
	private java.util.Date createTime;
	private java.util.Date updateTime;

}

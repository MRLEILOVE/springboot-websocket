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
	private Integer granularity;
	private java.time.LocalDateTime time;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;

}

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
public class TEntrustVO extends BaseVO<TEntrustVO> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long userId;
	private Integer currencyTradeId;
	private java.math.BigDecimal price;
	private java.math.BigDecimal amount;
	private java.math.BigDecimal successAmount;
	private java.math.BigDecimal count;
	private java.math.BigDecimal leftCount;
	private java.math.BigDecimal fees;
	private java.math.BigDecimal leftFees;
	private Integer status;
	private Integer entrustType;
	private Integer entrustDirection;
	private Integer version;
	private java.util.Date createTime;
	private java.util.Date updateTime;

}

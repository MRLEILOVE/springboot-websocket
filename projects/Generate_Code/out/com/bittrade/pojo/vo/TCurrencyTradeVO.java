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
public class TCurrencyTradeVO extends BaseVO<TCurrencyTradeVO> {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String symbol;
	private Integer currencyId1;
	private Integer currencyId2;
	private Integer priceDecimalDigits;
	private Integer countDecimalDigits;
	private java.math.BigDecimal minPrice;
	private java.math.BigDecimal minCount;
	private java.math.BigDecimal minAmount;
	private java.math.BigDecimal maxPrice;
	private java.math.BigDecimal maxCount;
	private java.math.BigDecimal maxAmount;
	private Byte status;
	private Integer sort;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;

}

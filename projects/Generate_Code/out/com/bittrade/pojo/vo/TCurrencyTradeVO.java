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

	private int id;
	private String symbol;
	private int currencyId1;
	private int currencyId2;
	private int priceDecimalDigits;
	private int countDecimalDigits;
	private java.math.BigDecimal minBuyCount;
	private java.math.BigDecimal minBuyPrice;
	private java.math.BigDecimal minBuyAmount;
	private java.math.BigDecimal maxBuyCount;
	private java.math.BigDecimal maxBuyAmount;
	private java.math.BigDecimal maxBuyPrice;
	private byte status;
	private int sort;
	private java.util.Date createTime;
	private java.util.Date updateTime;

}

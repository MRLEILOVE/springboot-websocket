package com.bittrade.pojo.dto;

import com.core.framework.base.DTO.BaseDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TCurrencyTradeDTO extends BaseDTO<TCurrencyTradeDTO> {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String symbol;
	private Integer currencyId1;
	private Integer currencyId2;
	private Integer priceDecimalDigits;
	private Integer countDecimalDigits;
	private java.math.BigDecimal minBuyPrice;
	private java.math.BigDecimal minBuyCount;
	private java.math.BigDecimal minBuyAmount;
	private java.math.BigDecimal maxBuyPrice;
	private java.math.BigDecimal maxBuyCount;
	private java.math.BigDecimal maxBuyAmount;
	private Byte status;
	private Integer sort;
	private java.util.Date createTime;
	private java.util.Date updateTime;

}

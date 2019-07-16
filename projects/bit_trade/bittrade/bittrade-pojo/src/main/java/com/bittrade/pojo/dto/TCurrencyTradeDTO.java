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
	private java.math.BigDecimal minPrice;
	private java.math.BigDecimal minCount;
	private java.math.BigDecimal minAmount;
	private java.math.BigDecimal maxPrice;
	private java.math.BigDecimal maxCount;
	private java.math.BigDecimal maxAmount;
	private Byte status;
	private Integer sort;
	private java.util.Date createTime;
	private java.util.Date updateTime;

}

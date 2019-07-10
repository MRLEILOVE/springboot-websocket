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
public class TEntrustDTO extends BaseDTO<TEntrustDTO> {

	private static final long serialVersionUID = 1L;

	private long id;
	private long userId;
	private int currencyTradeId;
	private java.math.BigDecimal price;
	private java.math.BigDecimal amount;
	private java.math.BigDecimal successAmount;
	private java.math.BigDecimal count;
	private java.math.BigDecimal leftCount;
	private java.math.BigDecimal fees;
	private java.math.BigDecimal leftFees;
	private int status;
	private int entrustType;
	private int entrustDirection;
	private int version;
	private java.util.Date createTime;
	private java.util.Date updateTime;

}

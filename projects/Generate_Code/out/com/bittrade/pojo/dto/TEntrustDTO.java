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
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;

}

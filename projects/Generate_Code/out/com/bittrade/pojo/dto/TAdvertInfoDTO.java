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
public class TAdvertInfoDTO extends BaseDTO<TAdvertInfoDTO> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long userId;
	private Long coinId;
	private Byte type;
	private Byte pricingMode;
	private java.math.BigDecimal floatingRatio;
	private java.math.BigDecimal price;
	private java.math.BigDecimal hidePrice;
	private java.math.BigDecimal minLimit;
	private java.math.BigDecimal maxLimit;
	private java.math.BigDecimal alreadyTransactionAmount;
	private java.math.BigDecimal balanceAmount;
	private java.math.BigDecimal freezeAmount;
	private Long paymentMethodId;
	private Byte status;
	private Byte openOpponentLimit;
	private Byte certificationLevel;
	private java.time.LocalDateTime registeredTime;
	private String message;
	private Long version;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;

}
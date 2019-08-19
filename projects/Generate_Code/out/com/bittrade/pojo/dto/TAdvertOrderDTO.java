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
public class TAdvertOrderDTO extends BaseDTO<TAdvertOrderDTO> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long advertId;
	private Long coinId;
	private String orderNumber;
	private String paymentLegalCurrency;
	private Byte advertType;
	private String advertMessage;
	private Long buyerId;
	private Long sellerId;
	private Long publisherId;
	private Long cancellerId;
	private java.math.BigDecimal transactionAmout;
	private java.math.BigDecimal transactionNum;
	private java.math.BigDecimal transactionPrice;
	private java.math.BigDecimal rate;
	private java.math.BigDecimal charge;
	private Byte status;
	private java.time.LocalDateTime cancelOrderDeadline;
	private Byte arbitStatus;
	private String arbitResult;
	private java.time.LocalDateTime paymentTime;
	private java.time.LocalDateTime grantCoinTime;
	private java.time.LocalDateTime overdueTime;
	private String remark;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;

}

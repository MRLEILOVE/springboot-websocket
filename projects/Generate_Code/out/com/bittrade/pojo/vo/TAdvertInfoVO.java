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
public class TAdvertInfoVO extends BaseVO<TAdvertInfoVO> {

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
	private Integer paymentTime;
	private String message;
	private Long version;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;

}

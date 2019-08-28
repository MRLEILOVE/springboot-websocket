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
public class TUserAccountDTO extends BaseDTO<TUserAccountDTO> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long userId;
	private String loginName;
	private String realName;
	private Byte fhasRealValidate;
	private java.math.BigDecimal balance;
	private java.math.BigDecimal balanceToken;
	private java.math.BigDecimal frozenAmount;
	private java.math.BigDecimal totalDeposits;
	private java.math.BigDecimal totalRechargeToken;
	private java.math.BigDecimal totalWithdrawals;
	private java.math.BigDecimal usedMargin;
	private java.math.BigDecimal usedToken;
	private Integer tokenNumber;
	private Byte internalAccount;
	private Integer version;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;

}

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
public class TWalletDTO extends BaseDTO<TWalletDTO> {

	private static final long serialVersionUID = 1L;

	private long id;
	private long userId;
	private int currencyId;
	private String token;
	private java.math.BigDecimal total;
	private java.math.BigDecimal tradeFrozen;
	private java.math.BigDecimal transferFrozen;
	private int version;
	private java.util.Date createTime;
	private java.util.Date updateTime;

}

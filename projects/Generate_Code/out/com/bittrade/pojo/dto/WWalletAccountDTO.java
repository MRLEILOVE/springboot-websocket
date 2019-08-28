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
public class WWalletAccountDTO extends BaseDTO<WWalletAccountDTO> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long userId;
	private Integer currencyId;
	private java.math.BigDecimal total;
	private java.math.BigDecimal tradeFrozen;
	private java.math.BigDecimal transferFrozen;
	private Integer version;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;

}

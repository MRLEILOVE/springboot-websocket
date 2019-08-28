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
public class TCurrencyOptionalDTO extends BaseDTO<TCurrencyOptionalDTO> {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Long userId;
	private Integer currencyTradeId;
	private Byte status;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;

}

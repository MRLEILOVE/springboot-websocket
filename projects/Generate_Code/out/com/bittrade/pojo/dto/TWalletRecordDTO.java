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
public class TWalletRecordDTO extends BaseDTO<TWalletRecordDTO> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long userId;
	private Integer currencyId;
	private java.math.BigDecimal beforeAmount;
	private java.math.BigDecimal afterAmount;
	private java.math.BigDecimal changeAmount;
	private Byte type;
	private Long entrustRecordId;
	private java.time.LocalDateTime createTime;

}

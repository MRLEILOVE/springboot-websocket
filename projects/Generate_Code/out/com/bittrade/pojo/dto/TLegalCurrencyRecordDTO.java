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
public class TLegalCurrencyRecordDTO extends BaseDTO<TLegalCurrencyRecordDTO> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long userId;
	private Integer coinId;
	private java.math.BigDecimal beforeAmount;
	private java.math.BigDecimal changeAmount;
	private java.math.BigDecimal afterAmount;
	private Integer type;
	private Byte bizOrderId;
	private java.time.LocalDateTime createTime;

}

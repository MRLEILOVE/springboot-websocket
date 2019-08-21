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
public class TWalletTransferDTO extends BaseDTO<TWalletTransferDTO> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long userId;
	private Integer currency;
	private java.math.BigDecimal count;
	private String businessNumber;
	private Byte status;
	private Byte typeChannel;
	private Byte sourceChannel;
	private String des;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;

}

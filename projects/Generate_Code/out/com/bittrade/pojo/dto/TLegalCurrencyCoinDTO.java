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
public class TLegalCurrencyCoinDTO extends BaseDTO<TLegalCurrencyCoinDTO> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long userId;
	private String name;
	private String title;
	private String img;
	private Byte status;
	private java.math.BigDecimal minQuota;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;

}

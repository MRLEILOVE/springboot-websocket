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
public class TLegalCurrencyAccountDTO extends BaseDTO<TLegalCurrencyAccountDTO> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long userId;
	private Integer coinId;
	private java.math.BigDecimal balanceAmount;
	private java.math.BigDecimal freezeAmount;
	private Integer c2cAlreadyDealCount;
	private Integer c2cTotalCount;
	private Integer version;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;

}

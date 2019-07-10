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
public class TEntrustRecordDTO extends BaseDTO<TEntrustRecordDTO> {

	private static final long serialVersionUID = 1L;

	private long id;
	private long userId;
	private long rivalUserId;
	private long entrustId;
	private long rivalEntrustId;
	private java.math.BigDecimal amount;
	private java.math.BigDecimal price;
	private java.math.BigDecimal count;
	private java.math.BigDecimal rate;
	private java.math.BigDecimal fees;
	private int currencyTradeId;
	private byte isActive;
	private int entrustDirection;
	private java.util.Date createTime;

}

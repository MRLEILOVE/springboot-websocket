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

	private int id;
	private long userId;
	private int currencyTradeId;
	private byte status;
	private java.util.Date createTime;
	private java.util.Date updateTime;

}

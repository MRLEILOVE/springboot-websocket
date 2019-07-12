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

	private long id;
	private long userId;
	private int currency;
	private java.math.BigDecimal count;
	private String businessNumber;
	private byte status;
	private byte typeChannel;
	private byte sourceChannel;
	private String desc;
	private java.util.Date createTime;
	private java.util.Date updateTime;

}

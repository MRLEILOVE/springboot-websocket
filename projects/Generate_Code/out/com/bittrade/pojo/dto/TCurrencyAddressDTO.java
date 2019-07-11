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
public class TCurrencyAddressDTO extends BaseDTO<TCurrencyAddressDTO> {

	private static final long serialVersionUID = 1L;

	private int id;
	private long userId;
	private int currencyId;
	private String adderess;
	private String addressRemark;
	private java.util.Date createTime;

}

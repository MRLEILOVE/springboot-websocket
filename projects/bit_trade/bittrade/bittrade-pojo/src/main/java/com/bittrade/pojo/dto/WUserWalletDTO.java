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
public class WUserWalletDTO extends BaseDTO<WUserWalletDTO> {

	private static final long serialVersionUID = 1L;

	private long id;
	private String platform;
	private long userId;
	private String coinType;
	private String address;
	private String privateKey;
	private byte flag;
	private String valid;
	private java.util.Date createTime;
	private java.util.Date updateTime;

}

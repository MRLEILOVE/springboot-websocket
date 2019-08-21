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

	private Long id;
	private String platform;
	private Long userId;
	private String coinType;
	private String address;
	private String privateKey;
	private String codeQr;
	private Byte flag;
	private String valid;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;

}

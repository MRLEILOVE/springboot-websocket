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
public class WConfigWalletDTO extends BaseDTO<WConfigWalletDTO> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String coinType;
	private String walletType;
	private String address;
	private String keystore;
	private String valid;
	private String remark;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;

}

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
public class TUserAuthenticationDTO extends BaseDTO<TUserAuthenticationDTO> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long userId;
	private String realName;
	private String identityNo;
	private Byte identityType;
	private String identityFrontUrl;
	private String identityFrontKey;
	private String identityFrontHash;
	private String identityBackUrl;
	private String identityBackKey;
	private String identityBackHash;
	private Byte postRealValidate;
	private Byte fhasRealValidate;
	private String bankCardNo;
	private String bankAddress;
	private String bankCardUrl;
	private String bankCardKey;
	private String bankCardHash;
	private String alipayNo;
	private Byte status;
	private java.time.LocalDateTime submitTime;
	private String auditor;
	private java.time.LocalDateTime auditTime;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;

}

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
public class WCoinDTO extends BaseDTO<WCoinDTO> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String coinType;
	private String token;
	private String contract;
	private String isRecharge;
	private String isWithdraw;
	private Byte status;
	private String remark;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;
	private String nameCN;

}

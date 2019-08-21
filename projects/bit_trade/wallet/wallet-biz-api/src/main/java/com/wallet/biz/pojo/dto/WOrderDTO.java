package com.wallet.biz.pojo.dto;

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
public class WOrderDTO extends BaseDTO<WOrderDTO> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long userId;
	private String orderId;
	private Integer orderType;
	private java.math.BigDecimal fee;
	private String coinType;
	private String token;
	private String contract;
	private java.math.BigDecimal amount;
	private String receiverAddress;
	private Integer type;
	private String operator;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;

}

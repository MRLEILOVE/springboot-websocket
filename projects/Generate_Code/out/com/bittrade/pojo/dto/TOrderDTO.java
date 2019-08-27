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
public class TOrderDTO extends BaseDTO<TOrderDTO> {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer userId;
	private String orderId;
	private java.math.BigDecimal fee;
	private String token;
	private java.math.BigDecimal amount;
	private String receiverAddress;
	private String type;
	private String operator;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;

}

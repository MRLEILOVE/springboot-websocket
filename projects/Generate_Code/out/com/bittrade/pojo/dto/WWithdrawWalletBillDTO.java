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
public class WWithdrawWalletBillDTO extends BaseDTO<WWithdrawWalletBillDTO> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long userId;
	private String platform;
	private String orderId;
	private Integer direction;
	private String coinType;
	private String token;
	private String contract;
	private String senderAddress;
	private String receiverAddress;
	private java.math.BigDecimal amount;
	private Long block;
	private String tx;
	private String tradeStep;
	private String operationType;
	private Byte transferFlag;
	private Byte flag;
	private String remark;
	private java.util.Date createTime;
	private java.util.Date updateTime;

}

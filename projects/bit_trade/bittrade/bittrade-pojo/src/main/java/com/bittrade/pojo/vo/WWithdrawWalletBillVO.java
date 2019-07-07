package com.bittrade.pojo.vo;

import com.core.framework.base.VO.BaseVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WWithdrawWalletBillVO extends BaseVO<WWithdrawWalletBillVO> {

	private static final long serialVersionUID = 1L;

	private long id;
	private long userId;
	private String platform;
	private String orderId;
	private int direction;
	private String coinType;
	private String token;
	private String contract;
	private String senderAddress;
	private String receiverAddress;
	private java.math.BigDecimal amount;
	private long block;
	private String tx;
	private String tradeStep;
	private String operationType;
	private byte transferFlag;
	private byte flag;
	private String remark;
	private java.util.Date createTime;
	private java.util.Date updateTime;

}

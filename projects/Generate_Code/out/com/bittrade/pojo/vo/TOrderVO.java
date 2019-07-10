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
public class TOrderVO extends BaseVO<TOrderVO> {

	private static final long serialVersionUID = 1L;

	private int id;
	private int userId;
	private String orderId;
	private java.math.BigDecimal fee;
	private String token;
	private java.math.BigDecimal amount;
	private String receiverAddress;
	private String type;
	private String operator;
	private java.util.Date createTime;
	private java.util.Date updateTime;

}

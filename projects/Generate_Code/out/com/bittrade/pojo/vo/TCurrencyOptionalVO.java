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
public class TCurrencyOptionalVO extends BaseVO<TCurrencyOptionalVO> {

	private static final long serialVersionUID = 1L;

	private int id;
	private long userId;
	private int currencyTradeId;
	private byte status;
	private java.util.Date createTime;
	private java.util.Date updateTime;

}

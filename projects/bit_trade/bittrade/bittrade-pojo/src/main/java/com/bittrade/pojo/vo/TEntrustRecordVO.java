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
public class TEntrustRecordVO extends BaseVO<TEntrustRecordVO> {

	private static final long serialVersionUID = 1L;

	private long id;
	private long userId;
	private long rivalUserId;
	private long entrustId;
	private long rivalEntrustId;
	private java.math.BigDecimal amount;
	private java.math.BigDecimal price;
	private java.math.BigDecimal count;
	private java.math.BigDecimal rate;
	private java.math.BigDecimal fees;
	private int currencyTradeId;
	private byte isActive;
	private int entrustType;
	private int version;
	private java.util.Date createTime;

}

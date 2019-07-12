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

	private Long id;
	private Long userId;
	private Long rivalUserId;
	private Long entrustId;
	private Long rivalEntrustId;
	private java.math.BigDecimal amount;
	private java.math.BigDecimal price;
	private java.math.BigDecimal count;
	private java.math.BigDecimal rate;
	private java.math.BigDecimal fees;
	private Integer currencyTradeId;
	private Byte isActive;
	private Integer entrustDirection;
	private java.util.Date createTime;

}

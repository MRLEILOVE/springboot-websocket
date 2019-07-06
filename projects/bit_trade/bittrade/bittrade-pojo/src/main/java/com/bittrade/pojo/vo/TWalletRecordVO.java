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
public class TWalletRecordVO extends BaseVO<TWalletRecordVO> {

	private static final long serialVersionUID = 1L;

	private long id;
	private long userId;
	private int currencyId;
	private java.math.BigDecimal beforeAmount;
	private java.math.BigDecimal afterAmount;
	private java.math.BigDecimal changeAmount;
	private byte type;
	private long entrustRecordId;
	private int version;
	private java.util.Date createTime;

}

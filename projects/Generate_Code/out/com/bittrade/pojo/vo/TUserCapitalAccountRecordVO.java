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
public class TUserCapitalAccountRecordVO extends BaseVO<TUserCapitalAccountRecordVO> {

	private static final long serialVersionUID = 1L;

	private long id;
	private long userId;
	private int currencyId;
	private java.math.BigDecimal count;
	private String adderess;
	private java.math.BigDecimal fees;
	private byte type;
	private byte status;
	private java.util.Date createTime;
	private java.util.Date updateTime;

}

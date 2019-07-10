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
public class TCurrencyAddressVO extends BaseVO<TCurrencyAddressVO> {

	private static final long serialVersionUID = 1L;

	private int id;
	private long userId;
	private int currencyId;
	private String adderess;
	private String addressRemark;
	private java.util.Date createTime;

}
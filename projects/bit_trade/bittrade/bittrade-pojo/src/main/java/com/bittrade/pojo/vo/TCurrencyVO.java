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
public class TCurrencyVO extends BaseVO<TCurrencyVO> {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String shortName;
	private byte status;
	private byte type;
	private String desc;
	private java.util.Date createTime;

}

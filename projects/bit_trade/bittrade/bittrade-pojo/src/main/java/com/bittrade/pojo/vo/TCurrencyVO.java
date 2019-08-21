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

	private Integer id;
	private String name;
	private String shortName;
	private Byte status;
	private Byte type;
	private Byte isRecharge;
	private Byte isWithdraw;
	private String desc;
	private java.time.LocalDateTime createTime;

}

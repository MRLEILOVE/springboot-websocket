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
public class TWalletTransferVO extends BaseVO<TWalletTransferVO> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long userId;
	private Integer currency;
	private java.math.BigDecimal count;
	private String businessNumber;
	private Byte status;
	private Byte typeChannel;
	private Byte sourceChannel;
	private String desc;
	private java.util.Date createTime;
	private java.util.Date updateTime;

}

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

	private Integer id;
	private Long userId;
	private Integer currencyTradeId;
	private Byte status;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;

}

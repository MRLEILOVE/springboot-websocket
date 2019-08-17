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
public class TLegalCurrencyCoinVO extends BaseVO<TLegalCurrencyCoinVO> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long userId;
	private String name;
	private String title;
	private String img;
	private Byte status;
	private java.math.BigDecimal minQuota;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;

}

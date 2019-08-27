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
public class TLegalCurrencyAccountVO extends BaseVO<TLegalCurrencyAccountVO> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long userId;
	private Integer coinId;
	private java.math.BigDecimal balanceAmount;
	private java.math.BigDecimal freezeAmount;
	private Integer c2cAlreadyDealCount;
	private Integer c2cTotalCount;
	private Integer version;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;

}

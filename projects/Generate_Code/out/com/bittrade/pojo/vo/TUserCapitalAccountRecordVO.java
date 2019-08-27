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

	private Long id;
	private Long userId;
	private Integer currencyId;
	private java.math.BigDecimal count;
	private String adderess;
	private java.math.BigDecimal fees;
	private Byte type;
	private Byte status;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;

}

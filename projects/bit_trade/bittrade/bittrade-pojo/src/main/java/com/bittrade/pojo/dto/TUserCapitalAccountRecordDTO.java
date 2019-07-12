package com.bittrade.pojo.dto;

import com.core.framework.base.DTO.BaseDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TUserCapitalAccountRecordDTO extends BaseDTO<TUserCapitalAccountRecordDTO> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long userId;
	private Integer currencyId;
	private java.math.BigDecimal count;
	private String adderess;
	private java.math.BigDecimal fees;
	private Byte type;
	private Byte status;
	private java.util.Date createTime;
	private java.util.Date updateTime;

}

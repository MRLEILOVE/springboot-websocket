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
public class TCurrencyDTO extends BaseDTO<TCurrencyDTO> {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String shortName;
	private Byte status;
	private Byte type;
	private String desc;
	private java.util.Date createTime;

}

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
public class TTickerDTO extends BaseDTO<TTickerDTO> {

	private static final long serialVersionUID = 1L;

	private int id;
	private String symbol;
	private String last;
	private java.util.Date createTime;

}

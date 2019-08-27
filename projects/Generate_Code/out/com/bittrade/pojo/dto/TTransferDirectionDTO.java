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
public class TTransferDirectionDTO extends BaseDTO<TTransferDirectionDTO> {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;
	private Integer mainAccountId;
	private Integer targetAccountId;
	private Integer status;

}

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
public class SysDictTypeDTO extends BaseDTO<SysDictTypeDTO> {

	private static final long serialVersionUID = 1L;

	private Integer dictId;
	private String dictName;
	private String dictType;
	private String status;
	private String createBy;
	private java.time.LocalDateTime createTime;
	private String updateBy;
	private java.time.LocalDateTime updateTime;
	private String remark;

}

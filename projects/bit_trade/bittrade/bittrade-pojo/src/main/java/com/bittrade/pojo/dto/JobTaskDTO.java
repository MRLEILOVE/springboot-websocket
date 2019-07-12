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
public class JobTaskDTO extends BaseDTO<JobTaskDTO> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String content;
	private Long sendTime;
	private Integer status;

}

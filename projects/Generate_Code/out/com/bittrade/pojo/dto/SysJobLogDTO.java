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
public class SysJobLogDTO extends BaseDTO<SysJobLogDTO> {

	private static final long serialVersionUID = 1L;

	private Long jobLogId;
	private String jobName;
	private String jobGroup;
	private String methodName;
	private String methodParams;
	private String jobMessage;
	private String status;
	private String exceptionInfo;
	private java.time.LocalDateTime createTime;

}

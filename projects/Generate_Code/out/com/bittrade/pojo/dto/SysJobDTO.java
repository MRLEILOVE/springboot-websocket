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
public class SysJobDTO extends BaseDTO<SysJobDTO> {

	private static final long serialVersionUID = 1L;

	private Long jobId;
	private String jobName;
	private String jobGroup;
	private String methodName;
	private String methodParams;
	private String cronExpression;
	private String misfirePolicy;
	private String concurrent;
	private String status;
	private String createBy;
	private java.time.LocalDateTime createTime;
	private String updateBy;
	private java.time.LocalDateTime updateTime;
	private String remark;

}

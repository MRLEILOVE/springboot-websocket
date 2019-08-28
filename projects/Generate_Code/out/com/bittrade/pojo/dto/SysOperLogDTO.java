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
public class SysOperLogDTO extends BaseDTO<SysOperLogDTO> {

	private static final long serialVersionUID = 1L;

	private Integer operId;
	private String title;
	private Integer businessType;
	private String method;
	private Integer operatorType;
	private String operName;
	private String deptName;
	private String operUrl;
	private String operIp;
	private String operLocation;
	private String operParam;
	private Integer status;
	private String errorMsg;
	private java.time.LocalDateTime operTime;

}

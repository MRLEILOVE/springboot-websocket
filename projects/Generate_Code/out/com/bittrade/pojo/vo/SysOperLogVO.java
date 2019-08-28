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
public class SysOperLogVO extends BaseVO<SysOperLogVO> {

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

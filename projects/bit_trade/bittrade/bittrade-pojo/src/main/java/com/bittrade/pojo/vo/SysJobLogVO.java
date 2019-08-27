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
public class SysJobLogVO extends BaseVO<SysJobLogVO> {

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

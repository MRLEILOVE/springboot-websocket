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
public class JobTaskVO extends BaseVO<JobTaskVO> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String content;
	private Long sendTime;
	private Integer status;

}

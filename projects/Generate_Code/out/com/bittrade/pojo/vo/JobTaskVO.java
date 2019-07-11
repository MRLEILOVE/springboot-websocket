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

	private long id;
	private String content;
	private long sendTime;
	private int status;

}

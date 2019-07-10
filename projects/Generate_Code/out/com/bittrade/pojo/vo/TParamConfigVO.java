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
public class TParamConfigVO extends BaseVO<TParamConfigVO> {

	private static final long serialVersionUID = 1L;

	private int id;
	private String paramKey;
	private String paramValue;
	private int paramStatus;
	private String paramRemark;
	private java.util.Date createTime;
	private String creater;
	private java.util.Date updateTime;
	private String updater;

}

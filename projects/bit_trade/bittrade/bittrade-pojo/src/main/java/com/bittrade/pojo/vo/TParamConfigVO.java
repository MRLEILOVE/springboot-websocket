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

	private Integer id;
	private String paramKey;
	private String paramValue;
	private Integer paramStatus;
	private String paramRemark;
	private java.time.LocalDateTime createTime;
	private String creater;
	private java.time.LocalDateTime updateTime;
	private String updater;

}

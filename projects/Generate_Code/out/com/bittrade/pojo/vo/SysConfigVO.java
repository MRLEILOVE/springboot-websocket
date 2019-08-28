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
public class SysConfigVO extends BaseVO<SysConfigVO> {

	private static final long serialVersionUID = 1L;

	private Integer configId;
	private String configName;
	private String configKey;
	private String configValue;
	private String configType;
	private String createBy;
	private java.time.LocalDateTime createTime;
	private String updateBy;
	private java.time.LocalDateTime updateTime;
	private String remark;

}

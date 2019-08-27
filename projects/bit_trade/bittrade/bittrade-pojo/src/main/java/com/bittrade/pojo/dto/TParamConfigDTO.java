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
public class TParamConfigDTO extends BaseDTO<TParamConfigDTO> {

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

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
public class TLoginLogDTO extends BaseDTO<TLoginLogDTO> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long userId;
	private Long loginName;
	private Byte lgoinType;
	private String loginIp;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;
	private Byte status;
	private String extend1;
	private String extend2;
	private String exten3;

}

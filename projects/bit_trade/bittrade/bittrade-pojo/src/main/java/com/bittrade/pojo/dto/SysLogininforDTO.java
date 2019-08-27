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
public class SysLogininforDTO extends BaseDTO<SysLogininforDTO> {

	private static final long serialVersionUID = 1L;

	private Integer infoId;
	private String loginName;
	private String ipaddr;
	private String loginLocation;
	private String browser;
	private String os;
	private String status;
	private String msg;
	private java.time.LocalDateTime loginTime;

}

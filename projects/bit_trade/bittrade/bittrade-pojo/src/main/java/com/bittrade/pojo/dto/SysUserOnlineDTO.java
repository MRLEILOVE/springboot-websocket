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
public class SysUserOnlineDTO extends BaseDTO<SysUserOnlineDTO> {

	private static final long serialVersionUID = 1L;

	private String sessionId;
	private String loginName;
	private String deptName;
	private String ipaddr;
	private String loginLocation;
	private String browser;
	private String os;
	private String status;
	private java.time.LocalDateTime startTimestamp;
	private java.time.LocalDateTime lastAccessTime;
	private Integer expireTime;

}

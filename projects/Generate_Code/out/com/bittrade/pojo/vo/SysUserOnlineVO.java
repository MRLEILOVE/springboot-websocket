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
public class SysUserOnlineVO extends BaseVO<SysUserOnlineVO> {

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

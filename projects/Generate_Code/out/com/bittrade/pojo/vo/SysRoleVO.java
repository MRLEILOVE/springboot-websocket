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
public class SysRoleVO extends BaseVO<SysRoleVO> {

	private static final long serialVersionUID = 1L;

	private Integer roleId;
	private String roleName;
	private String roleKey;
	private Integer roleSort;
	private Byte dataScope;
	private Byte status;
	private Byte delFlag;
	private String createBy;
	private java.time.LocalDateTime createTime;
	private String updateBy;
	private java.time.LocalDateTime updateTime;
	private String remark;

}

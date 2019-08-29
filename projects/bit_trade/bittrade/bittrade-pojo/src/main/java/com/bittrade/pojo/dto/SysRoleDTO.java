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
public class SysRoleDTO extends BaseDTO<SysRoleDTO> {

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

	/** 用户是否存在此角色标识 默认不存在 */
	private boolean flag = false;

	/** 菜单组 */
	private Integer[] menuIds;

	/** 部门组（数据权限） */
	private Integer[] deptIds;

}

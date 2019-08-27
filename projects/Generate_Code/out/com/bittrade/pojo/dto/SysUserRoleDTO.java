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
public class SysUserRoleDTO extends BaseDTO<SysUserRoleDTO> {

	private static final long serialVersionUID = 1L;

	private Integer userId;
	private Integer roleId;

}

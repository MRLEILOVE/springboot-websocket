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
public class SysRoleDeptDTO extends BaseDTO<SysRoleDeptDTO> {

	private static final long serialVersionUID = 1L;

	private Integer roleId;
	private Integer deptId;

}

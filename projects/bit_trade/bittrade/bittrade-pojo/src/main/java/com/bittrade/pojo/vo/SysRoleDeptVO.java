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
public class SysRoleDeptVO extends BaseVO<SysRoleDeptVO> {

	private static final long serialVersionUID = 1L;

	private Integer roleId;
	private Integer deptId;

}

package com.bittrade.admin;

import com.bittrade.pojo.dto.SysUserDTO;
import com.bittrade.pojo.model.SysUser;
import com.core.tool.BeanUtil;

public class MainTester {
	
	public static void main(String[] args) {
		SysUserDTO sysUserDTO = new SysUserDTO();
		sysUserDTO.setDeptId(3);
		System.out.println(sysUserDTO);
		SysUser sysUser = new SysUser();
		BeanUtil.copyObj(sysUserDTO, sysUser);
		System.out.println(sysUser);
		System.out.println(sysUser.getDeptId());
	}
	
}

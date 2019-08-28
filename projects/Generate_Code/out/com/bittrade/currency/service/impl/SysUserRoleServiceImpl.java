package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultSysUserRoleServiceImpl;
import com.bittrade.currency.dao.ISysUserRoleDAO;
import com.bittrade.pojo.dto.SysUserRoleDTO;
import com.bittrade.pojo.vo.SysUserRoleVO;
import com.bittrade.pojo.model.SysUserRole;
import com.bittrade.currency.api.service.ISysUserRoleService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class SysUserRoleServiceImpl extends DefaultSysUserRoleServiceImpl<ISysUserRoleDAO, SysUserRole, SysUserRoleDTO, SysUserRoleVO> implements ISysUserRoleService {
	
}

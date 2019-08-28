package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultSysRoleServiceImpl;
import com.bittrade.currency.dao.ISysRoleDAO;
import com.bittrade.pojo.dto.SysRoleDTO;
import com.bittrade.pojo.vo.SysRoleVO;
import com.bittrade.pojo.model.SysRole;
import com.bittrade.currency.api.service.ISysRoleService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class SysRoleServiceImpl extends DefaultSysRoleServiceImpl<ISysRoleDAO, SysRole, SysRoleDTO, SysRoleVO> implements ISysRoleService {
	
}

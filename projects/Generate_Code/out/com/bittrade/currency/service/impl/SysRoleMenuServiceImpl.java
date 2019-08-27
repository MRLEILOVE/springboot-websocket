package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultSysRoleMenuServiceImpl;
import com.bittrade.currency.dao.ISysRoleMenuDAO;
import com.bittrade.pojo.dto.SysRoleMenuDTO;
import com.bittrade.pojo.vo.SysRoleMenuVO;
import com.bittrade.pojo.model.SysRoleMenu;
import com.bittrade.currency.api.service.ISysRoleMenuService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class SysRoleMenuServiceImpl extends DefaultSysRoleMenuServiceImpl<ISysRoleMenuDAO, SysRoleMenu, SysRoleMenuDTO, SysRoleMenuVO> implements ISysRoleMenuService {
	
}

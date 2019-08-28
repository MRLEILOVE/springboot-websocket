package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultSysMenuServiceImpl;
import com.bittrade.currency.dao.ISysMenuDAO;
import com.bittrade.pojo.dto.SysMenuDTO;
import com.bittrade.pojo.vo.SysMenuVO;
import com.bittrade.pojo.model.SysMenu;
import com.bittrade.currency.api.service.ISysMenuService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class SysMenuServiceImpl extends DefaultSysMenuServiceImpl<ISysMenuDAO, SysMenu, SysMenuDTO, SysMenuVO> implements ISysMenuService {
	
}

package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultSysUserServiceImpl;
import com.bittrade.currency.dao.ISysUserDAO;
import com.bittrade.pojo.dto.SysUserDTO;
import com.bittrade.pojo.vo.SysUserVO;
import com.bittrade.pojo.model.SysUser;
import com.bittrade.currency.api.service.ISysUserService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class SysUserServiceImpl extends DefaultSysUserServiceImpl<ISysUserDAO, SysUser, SysUserDTO, SysUserVO> implements ISysUserService {
	
}

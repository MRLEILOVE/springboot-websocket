package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultSysRoleDeptServiceImpl;
import com.bittrade.currency.dao.ISysRoleDeptDAO;
import com.bittrade.pojo.dto.SysRoleDeptDTO;
import com.bittrade.pojo.vo.SysRoleDeptVO;
import com.bittrade.pojo.model.SysRoleDept;
import com.bittrade.currency.api.service.ISysRoleDeptService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class SysRoleDeptServiceImpl extends DefaultSysRoleDeptServiceImpl<ISysRoleDeptDAO, SysRoleDept, SysRoleDeptDTO, SysRoleDeptVO> implements ISysRoleDeptService {
	
}

package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultSysUserOnlineServiceImpl;
import com.bittrade.currency.dao.ISysUserOnlineDAO;
import com.bittrade.pojo.dto.SysUserOnlineDTO;
import com.bittrade.pojo.vo.SysUserOnlineVO;
import com.bittrade.pojo.model.SysUserOnline;
import com.bittrade.currency.api.service.ISysUserOnlineService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class SysUserOnlineServiceImpl extends DefaultSysUserOnlineServiceImpl<ISysUserOnlineDAO, SysUserOnline, SysUserOnlineDTO, SysUserOnlineVO> implements ISysUserOnlineService {
	
}

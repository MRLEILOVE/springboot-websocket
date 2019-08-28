package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultSysOperLogServiceImpl;
import com.bittrade.currency.dao.ISysOperLogDAO;
import com.bittrade.pojo.dto.SysOperLogDTO;
import com.bittrade.pojo.vo.SysOperLogVO;
import com.bittrade.pojo.model.SysOperLog;
import com.bittrade.currency.api.service.ISysOperLogService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class SysOperLogServiceImpl extends DefaultSysOperLogServiceImpl<ISysOperLogDAO, SysOperLog, SysOperLogDTO, SysOperLogVO> implements ISysOperLogService {
	
}

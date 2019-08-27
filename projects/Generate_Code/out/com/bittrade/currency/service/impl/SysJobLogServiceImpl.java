package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultSysJobLogServiceImpl;
import com.bittrade.currency.dao.ISysJobLogDAO;
import com.bittrade.pojo.dto.SysJobLogDTO;
import com.bittrade.pojo.vo.SysJobLogVO;
import com.bittrade.pojo.model.SysJobLog;
import com.bittrade.currency.api.service.ISysJobLogService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class SysJobLogServiceImpl extends DefaultSysJobLogServiceImpl<ISysJobLogDAO, SysJobLog, SysJobLogDTO, SysJobLogVO> implements ISysJobLogService {
	
}

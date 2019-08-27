package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTLoginLogServiceImpl;
import com.bittrade.currency.dao.ITLoginLogDAO;
import com.bittrade.pojo.dto.TLoginLogDTO;
import com.bittrade.pojo.vo.TLoginLogVO;
import com.bittrade.pojo.model.TLoginLog;
import com.bittrade.currency.api.service.ITLoginLogService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TLoginLogServiceImpl extends DefaultTLoginLogServiceImpl<ITLoginLogDAO, TLoginLog, TLoginLogDTO, TLoginLogVO> implements ITLoginLogService {
	
}

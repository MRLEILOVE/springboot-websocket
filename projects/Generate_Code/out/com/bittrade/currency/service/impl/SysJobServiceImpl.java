package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultSysJobServiceImpl;
import com.bittrade.currency.dao.ISysJobDAO;
import com.bittrade.pojo.dto.SysJobDTO;
import com.bittrade.pojo.vo.SysJobVO;
import com.bittrade.pojo.model.SysJob;
import com.bittrade.currency.api.service.ISysJobService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class SysJobServiceImpl extends DefaultSysJobServiceImpl<ISysJobDAO, SysJob, SysJobDTO, SysJobVO> implements ISysJobService {
	
}

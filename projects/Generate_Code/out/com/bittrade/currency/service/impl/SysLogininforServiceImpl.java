package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultSysLogininforServiceImpl;
import com.bittrade.currency.dao.ISysLogininforDAO;
import com.bittrade.pojo.dto.SysLogininforDTO;
import com.bittrade.pojo.vo.SysLogininforVO;
import com.bittrade.pojo.model.SysLogininfor;
import com.bittrade.currency.api.service.ISysLogininforService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class SysLogininforServiceImpl extends DefaultSysLogininforServiceImpl<ISysLogininforDAO, SysLogininfor, SysLogininforDTO, SysLogininforVO> implements ISysLogininforService {
	
}

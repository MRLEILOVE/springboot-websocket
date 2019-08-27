package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultSysConfigServiceImpl;
import com.bittrade.currency.dao.ISysConfigDAO;
import com.bittrade.pojo.dto.SysConfigDTO;
import com.bittrade.pojo.vo.SysConfigVO;
import com.bittrade.pojo.model.SysConfig;
import com.bittrade.currency.api.service.ISysConfigService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class SysConfigServiceImpl extends DefaultSysConfigServiceImpl<ISysConfigDAO, SysConfig, SysConfigDTO, SysConfigVO> implements ISysConfigService {
	
}

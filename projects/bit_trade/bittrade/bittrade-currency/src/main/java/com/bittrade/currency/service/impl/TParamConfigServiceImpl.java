package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTParamConfigServiceImpl;
import com.bittrade.currency.api.service.ITParamConfigService;
import com.bittrade.currency.dao.ITParamConfigDAO;
import com.bittrade.pojo.dto.TParamConfigDTO;
import com.bittrade.pojo.vo.TParamConfigVO;
import com.bittrade.pojo.model.TParamConfig;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TParamConfigServiceImpl extends DefaultTParamConfigServiceImpl<ITParamConfigDAO, TParamConfig, TParamConfigDTO, TParamConfigVO> implements ITParamConfigService {
	
}

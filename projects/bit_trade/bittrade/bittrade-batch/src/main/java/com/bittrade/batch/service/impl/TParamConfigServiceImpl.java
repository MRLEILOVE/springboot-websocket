package com.bittrade.batch.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.api.__default.service.impl.DefaultTParamConfigServiceImpl;
import com.bittrade.api.dao.ITParamConfigDAO;
import com.bittrade.pojo.dto.TParamConfigDTO;
import com.bittrade.pojo.vo.TParamConfigVO;
import com.bittrade.pojo.model.TParamConfig;
import com.bittrade.api.service.ITParamConfigService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TParamConfigServiceImpl extends DefaultTParamConfigServiceImpl<ITParamConfigDAO, TParamConfig, TParamConfigDTO, TParamConfigVO>
		implements ITParamConfigService {

}

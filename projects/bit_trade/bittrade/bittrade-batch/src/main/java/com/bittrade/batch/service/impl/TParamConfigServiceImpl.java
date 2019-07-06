package com.bittrade.batch.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.api.__default.DAO.IDefaultTParamConfigDAO;
import com.bittrade.api.__default.service.impl.DefaultTParamConfigServiceImpl;
import com.bittrade.api.service.ITParamConfigService;
import com.bittrade.pojo.dto.TParamConfigDTO;
import com.bittrade.pojo.model.TParamConfig;
import com.bittrade.pojo.vo.TParamConfigVO;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TParamConfigServiceImpl extends DefaultTParamConfigServiceImpl<IDefaultTParamConfigDAO, TParamConfig, TParamConfigDTO, TParamConfigVO>
		implements ITParamConfigService {

}

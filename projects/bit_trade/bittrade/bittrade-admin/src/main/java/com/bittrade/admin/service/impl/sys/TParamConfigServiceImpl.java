package com.bittrade.admin.service.impl.sys;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTParamConfigServiceImpl;
import com.bittrade.pojo.dto.TParamConfigDTO;
import com.bittrade.pojo.vo.TParamConfigVO;
import com.bittrade.pojo.model.TParamConfig;
import com.bittrade.admin.dao.sys.ITParamConfigDAO;
import com.bittrade.admin.service.sys.ITParamConfigService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TParamConfigServiceImpl extends DefaultTParamConfigServiceImpl<ITParamConfigDAO, TParamConfig, TParamConfigDTO, TParamConfigVO> implements ITParamConfigService {
	
}

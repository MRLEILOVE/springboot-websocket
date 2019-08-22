package com.bittrade.admin.service.impl.wallet;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultWCoinConfigServiceImpl;
import com.bittrade.pojo.dto.WCoinConfigDTO;
import com.bittrade.pojo.vo.WCoinConfigVO;
import com.bittrade.pojo.model.WCoinConfig;
import com.bittrade.admin.dao.wallet.IWCoinConfigDAO;
import com.bittrade.admin.service.wallet.IWCoinConfigService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class WCoinConfigServiceImpl extends DefaultWCoinConfigServiceImpl<IWCoinConfigDAO, WCoinConfig, WCoinConfigDTO, WCoinConfigVO> implements IWCoinConfigService {
	
}

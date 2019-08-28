package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultWCoinServiceImpl;
import com.bittrade.currency.dao.IWCoinDAO;
import com.bittrade.pojo.dto.WCoinDTO;
import com.bittrade.pojo.vo.WCoinVO;
import com.bittrade.pojo.model.WCoin;
import com.bittrade.currency.api.service.IWCoinService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class WCoinServiceImpl extends DefaultWCoinServiceImpl<IWCoinDAO, WCoin, WCoinDTO, WCoinVO> implements IWCoinService {
	
}

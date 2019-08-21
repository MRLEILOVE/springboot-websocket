package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTLegalCurrencyCoinServiceImpl;
import com.bittrade.currency.dao.ITLegalCurrencyCoinDAO;
import com.bittrade.pojo.dto.TLegalCurrencyCoinDTO;
import com.bittrade.pojo.vo.TLegalCurrencyCoinVO;
import com.bittrade.pojo.model.TLegalCurrencyCoin;
import com.bittrade.currency.api.service.ITLegalCurrencyCoinService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TLegalCurrencyCoinServiceImpl extends DefaultTLegalCurrencyCoinServiceImpl<ITLegalCurrencyCoinDAO, TLegalCurrencyCoin, TLegalCurrencyCoinDTO, TLegalCurrencyCoinVO> implements ITLegalCurrencyCoinService {
	
}

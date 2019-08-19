package com.bittrade.c2c.service.impl;

import com.bittrade.__default.service.impl.DefaultTLegalCurrencyCoinServiceImpl;
import com.bittrade.c2c.dao.ITLegalCurrencyCoinDAO;
import com.bittrade.c2c.service.ITLegalCurrencyCoinService;
import com.bittrade.pojo.dto.TLegalCurrencyCoinDTO;
import com.bittrade.pojo.model.TLegalCurrencyCoin;
import com.bittrade.pojo.vo.TLegalCurrencyCoinVO;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TLegalCurrencyCoinServiceImpl extends DefaultTLegalCurrencyCoinServiceImpl<ITLegalCurrencyCoinDAO, TLegalCurrencyCoin, TLegalCurrencyCoinDTO, TLegalCurrencyCoinVO> implements ITLegalCurrencyCoinService {
	
}

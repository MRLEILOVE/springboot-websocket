package com.bittrade.admin.service.impl.currency;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTLegalCurrencyCoinServiceImpl;
import com.bittrade.pojo.dto.TLegalCurrencyCoinDTO;
import com.bittrade.pojo.vo.TLegalCurrencyCoinVO;
import com.bittrade.pojo.model.TLegalCurrencyCoin;
import com.bittrade.admin.dao.currency.ITLegalCurrencyCoinDAO;
import com.bittrade.admin.service.currency.ITLegalCurrencyCoinService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TLegalCurrencyCoinServiceImpl extends DefaultTLegalCurrencyCoinServiceImpl<ITLegalCurrencyCoinDAO, TLegalCurrencyCoin, TLegalCurrencyCoinDTO, TLegalCurrencyCoinVO> implements ITLegalCurrencyCoinService {
	
}

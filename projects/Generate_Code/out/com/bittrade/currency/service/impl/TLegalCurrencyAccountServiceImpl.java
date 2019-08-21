package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTLegalCurrencyAccountServiceImpl;
import com.bittrade.currency.dao.ITLegalCurrencyAccountDAO;
import com.bittrade.pojo.dto.TLegalCurrencyAccountDTO;
import com.bittrade.pojo.vo.TLegalCurrencyAccountVO;
import com.bittrade.pojo.model.TLegalCurrencyAccount;
import com.bittrade.currency.api.service.ITLegalCurrencyAccountService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TLegalCurrencyAccountServiceImpl extends DefaultTLegalCurrencyAccountServiceImpl<ITLegalCurrencyAccountDAO, TLegalCurrencyAccount, TLegalCurrencyAccountDTO, TLegalCurrencyAccountVO> implements ITLegalCurrencyAccountService {
	
}

package com.bittrade.c2c.service.impl;

import com.bittrade.__default.service.impl.DefaultTLegalCurrencyAccountServiceImpl;
import com.bittrade.c2c.dao.ITLegalCurrencyAccountDAO;
import com.bittrade.c2c.service.ITLegalCurrencyAccountService;
import com.bittrade.pojo.dto.TLegalCurrencyAccountDTO;
import com.bittrade.pojo.model.TLegalCurrencyAccount;
import com.bittrade.pojo.vo.TLegalCurrencyAccountVO;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TLegalCurrencyAccountServiceImpl extends DefaultTLegalCurrencyAccountServiceImpl<ITLegalCurrencyAccountDAO, TLegalCurrencyAccount, TLegalCurrencyAccountDTO, TLegalCurrencyAccountVO> implements ITLegalCurrencyAccountService {
	
}

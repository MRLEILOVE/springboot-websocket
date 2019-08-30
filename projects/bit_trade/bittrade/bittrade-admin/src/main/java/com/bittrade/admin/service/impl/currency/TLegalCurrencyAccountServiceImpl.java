package com.bittrade.admin.service.impl.currency;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTLegalCurrencyAccountServiceImpl;
import com.bittrade.pojo.dto.TLegalCurrencyAccountDTO;
import com.bittrade.pojo.vo.TLegalCurrencyAccountVO;
import com.bittrade.pojo.model.TLegalCurrencyAccount;
import com.bittrade.admin.dao.currency.ITLegalCurrencyAccountDAO;
import com.bittrade.admin.service.currency.ITLegalCurrencyAccountService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TLegalCurrencyAccountServiceImpl extends DefaultTLegalCurrencyAccountServiceImpl<ITLegalCurrencyAccountDAO, TLegalCurrencyAccount, TLegalCurrencyAccountDTO, TLegalCurrencyAccountVO> implements ITLegalCurrencyAccountService {
	
}

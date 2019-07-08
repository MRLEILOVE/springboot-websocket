package com.bittrade.currency.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bittrade.api.__default.service.impl.DefaultTCurrencyTradeServiceImpl;
import com.bittrade.api.service.ITCurrencyTradeService;
import com.bittrade.currency.dao.ITCurrencyTradeDAO;
import com.bittrade.pojo.dto.TCurrencyTradeDTO;
import com.bittrade.pojo.model.TCurrencyTrade;
import com.bittrade.pojo.vo.TCurrencyTradeVO;
import com.bittrade.pojo.vo.TransactionPairVO;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TCurrencyTradeServiceImpl extends
		DefaultTCurrencyTradeServiceImpl<ITCurrencyTradeDAO, TCurrencyTrade, TCurrencyTradeDTO, TCurrencyTradeVO>
		implements ITCurrencyTradeService<ITCurrencyTradeDAO> {

	@Autowired
	private ITCurrencyTradeDAO tCurrencyTradeDAO;

	/**
	 * 根据法币id查找交易对
	 */
	@Override
	public List<TransactionPairVO> findTradeByCurrencyId2(String currencyId2) {
		return tCurrencyTradeDAO.findTradeByCurrencyId2(currencyId2);
	}
}

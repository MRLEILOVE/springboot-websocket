package com.bittrade.currency.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bittrade.api.__default.DAO.IDefaultTCurrencyTradeDAO;
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
		DefaultTCurrencyTradeServiceImpl<IDefaultTCurrencyTradeDAO, TCurrencyTrade, TCurrencyTradeDTO, TCurrencyTradeVO>
		implements ITCurrencyTradeService {

	@Autowired
	private ITCurrencyTradeDAO tCurrencyTradeDAO;

	/**
	 * 根据法币id查找交易对
	 */
	@Override
	public List<TransactionPairVO> findTradeByCurrencyId(String currencyId2) {
		return tCurrencyTradeDAO.findTradeByCurrencyId(currencyId2);
	}
}

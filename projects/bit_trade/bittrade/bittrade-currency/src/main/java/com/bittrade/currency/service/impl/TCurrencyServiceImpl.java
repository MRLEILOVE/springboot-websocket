package com.bittrade.currency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bittrade.api.__default.service.impl.DefaultTCurrencyServiceImpl;
import com.bittrade.api.dao.ITCurrencyDAO;
import com.bittrade.pojo.dto.TCurrencyDTO;
import com.bittrade.pojo.vo.TCurrencyVO;
import com.bittrade.pojo.model.TCurrency;
import com.bittrade.api.service.ITCurrencyService;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TCurrencyServiceImpl extends
		DefaultTCurrencyServiceImpl<ITCurrencyDAO, TCurrency, TCurrencyDTO, TCurrencyVO> implements ITCurrencyService {

	@Autowired
	private ITCurrencyDAO tCurrencyDAO;

	/**
	 * 查找所有法币
	 */
	@Override
	public List<TCurrencyVO> findAllLegalCurrency() {
		return tCurrencyDAO.findAllLegalCurrency();
	}
}

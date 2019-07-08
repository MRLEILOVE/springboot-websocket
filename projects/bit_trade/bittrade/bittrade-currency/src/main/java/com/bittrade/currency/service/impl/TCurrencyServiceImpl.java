package com.bittrade.currency.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bittrade.api.__default.service.impl.DefaultTCurrencyServiceImpl;
import com.bittrade.api.service.ITCurrencyService;
import com.bittrade.currency.dao.ITCurrencyDAO;
import com.bittrade.pojo.dto.TCurrencyDTO;
import com.bittrade.pojo.model.TCurrency;
import com.bittrade.pojo.vo.TCurrencyVO;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TCurrencyServiceImpl extends
		DefaultTCurrencyServiceImpl<ITCurrencyDAO, TCurrency, TCurrencyDTO, TCurrencyVO> implements ITCurrencyService<ITCurrencyDAO> {

	@Autowired
	private ITCurrencyDAO tCurrencyDAO;

	/**
	 * 查找所有法币
	 */
	@Override
	public List<TCurrency> findAllLegalCurrency() {
		QueryWrapper<TCurrency> wrapper = new QueryWrapper<>();
		wrapper.eq("status",1).select("id","name");
		return tCurrencyDAO.selectList(wrapper);
	}
}

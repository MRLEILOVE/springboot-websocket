package com.bittrade.currency.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bittrade.__default.service.impl.DefaultTCurrencyServiceImpl;
import com.bittrade.currency.api.service.ITCurrencyService;
import com.bittrade.currency.dao.ITCurrencyDAO;
import com.bittrade.pojo.model.TCurrency;

/**
 * 
 * @author Administrator
 *
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class TCurrencyServiceImpl extends
		DefaultTCurrencyServiceImpl<ITCurrencyDAO> implements ITCurrencyService {

	@Autowired
	private ITCurrencyDAO tCurrencyDAO;

	/**
	 * 查找所有法币
	 */
	@Override
	public List<TCurrency> findAllLegalCurrency() {
		QueryWrapper<TCurrency> wrapper = new QueryWrapper<>();
		wrapper.eq(TCurrency.FieldNames.STATUS,1)
				.eq(TCurrency.FieldNames.TYPE,1)
				.select(TCurrency.FieldNames.ID,TCurrency.FieldNames.NAME);
		return tCurrencyDAO.selectList(wrapper);
	}

	/**
	 * 查找所有可用币种
	 */
    @Override
    public List<String> findUsableCurrency() {
        return tCurrencyDAO.findUsableCurrency();
    }

	/**
	 * 获取币种列表
	 * @return
	 */
	@Override
	public List<TCurrency> getCurrencies() {
		return tCurrencyDAO.gets();
	}

	/**
	 * 通过名称获取币种列表
	 * @return
	 */
	@Override
	public List<TCurrency> getCurrencies(List<String> list) {
		return tCurrencyDAO.getCurrencies(list);
	}
}

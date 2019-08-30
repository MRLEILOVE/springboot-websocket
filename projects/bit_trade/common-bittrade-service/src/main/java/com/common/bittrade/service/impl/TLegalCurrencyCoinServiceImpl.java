package com.common.bittrade.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bittrade.__default.service.impl.DefaultTLegalCurrencyCoinServiceImpl;
import com.bittrade.pojo.model.TLegalCurrencyCoin;
import com.bittrade.pojo.vo.LegalCurrencyCoinVO;
import com.common.bittrade.dao.ITLegalCurrencyCoinDAO;
import com.common.bittrade.service.ITLegalCurrencyCoinService;
import com.google.common.collect.Lists;

/**
 * @author Administrator
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class TLegalCurrencyCoinServiceImpl extends DefaultTLegalCurrencyCoinServiceImpl<ITLegalCurrencyCoinDAO> implements ITLegalCurrencyCoinService {
	@Autowired
	private ITLegalCurrencyCoinDAO currencyCoinDAO;

	/**
	 * 获取法币虚拟币列表
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/20 18:49
	 *
	 * @return
	 */
	@Override
	public List<LegalCurrencyCoinVO> listLegalCurrencyCoins() {
		List<TLegalCurrencyCoin> tLegalCurrencyCoins = baseMapper.selectList(
				new LambdaQueryWrapper<TLegalCurrencyCoin>()
						.select(
								TLegalCurrencyCoin::getId,
								TLegalCurrencyCoin::getName,
								TLegalCurrencyCoin::getTitle,
								TLegalCurrencyCoin::getImg,
								TLegalCurrencyCoin::getStatus
						)
		);
		List<LegalCurrencyCoinVO> legalCurrencyCoinVOs = Lists.newArrayList();
		tLegalCurrencyCoins.forEach(tLegalCurrencyCoin -> {
			LegalCurrencyCoinVO legalCurrencyCoinVO = LegalCurrencyCoinVO.builder().build();
			BeanUtils.copyProperties(tLegalCurrencyCoin, legalCurrencyCoinVO);
			legalCurrencyCoinVOs.add(legalCurrencyCoinVO);
		});
		return legalCurrencyCoinVOs;
	}

	/**
	 * 通过币种名称获取币种
	 * @param currencyName 币种名称
	 * @return 币种对象
	 */
	@Override
	public TLegalCurrencyCoin getByName(String currencyName) {
		TLegalCurrencyCoin qryCoin = TLegalCurrencyCoin.builder().name(currencyName).build();
		return currencyCoinDAO.getBy(qryCoin);
	}
}

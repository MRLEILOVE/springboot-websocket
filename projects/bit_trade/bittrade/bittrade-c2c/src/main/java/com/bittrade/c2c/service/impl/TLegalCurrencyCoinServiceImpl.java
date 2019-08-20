package com.bittrade.c2c.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bittrade.__default.service.impl.DefaultTLegalCurrencyCoinServiceImpl;
import com.bittrade.c2c.dao.ITLegalCurrencyCoinDAO;
import com.bittrade.c2c.service.ITLegalCurrencyCoinService;
import com.bittrade.pojo.dto.TLegalCurrencyCoinDTO;
import com.bittrade.pojo.model.TLegalCurrencyCoin;
import com.bittrade.pojo.vo.LegalCurrencyCoinVO;
import com.bittrade.pojo.vo.TLegalCurrencyCoinVO;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class TLegalCurrencyCoinServiceImpl extends DefaultTLegalCurrencyCoinServiceImpl<ITLegalCurrencyCoinDAO, TLegalCurrencyCoin, TLegalCurrencyCoinDTO, TLegalCurrencyCoinVO> implements ITLegalCurrencyCoinService {

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
}

package com.bittrade.c2c.service;

import com.bittrade.__default.service.IDefaultTLegalCurrencyCoinService;
import com.bittrade.pojo.dto.TLegalCurrencyCoinDTO;
import com.bittrade.pojo.vo.LegalCurrencyCoinVO;
import com.bittrade.pojo.vo.TLegalCurrencyCoinVO;
import com.bittrade.pojo.model.TLegalCurrencyCoin;
import com.core.common.DTO.ReturnDTO;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public interface ITLegalCurrencyCoinService extends IDefaultTLegalCurrencyCoinService<TLegalCurrencyCoin, TLegalCurrencyCoinDTO, TLegalCurrencyCoinVO> {

	/**
	 * 获取法币虚拟币列表
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/20 18:49
	 * @return
	 */
	List<LegalCurrencyCoinVO> listLegalCurrencyCoins();
}

package com.common.bittrade.service;

import java.util.List;

import com.bittrade.__default.service.IDefaultTLegalCurrencyCoinService;
import com.bittrade.pojo.model.TLegalCurrencyCoin;
import com.bittrade.pojo.vo.LegalCurrencyCoinVO;

/**
 * 
 * @author Administrator
 *
 */
public interface ITLegalCurrencyCoinService extends IDefaultTLegalCurrencyCoinService {

	/**
	 * 获取法币虚拟币列表
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/20 18:49
	 * @return
	 */
	List<LegalCurrencyCoinVO> listLegalCurrencyCoins();

	/**
	 * 通过币种名称获取币种
	 * @param currencyName 币种名称
	 * @return 币种对象
	 */
    TLegalCurrencyCoin getByName(String currencyName);
}

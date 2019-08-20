package com.bittrade.c2c.controller;

import com.bittrade.c2c.service.ITLegalCurrencyCoinService;
import com.bittrade.pojo.dto.TLegalCurrencyCoinDTO;
import com.bittrade.pojo.model.TLegalCurrencyCoin;
import com.bittrade.pojo.vo.TLegalCurrencyCoinVO;
import com.core.common.DTO.ReturnDTO;
import com.core.framework.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 法币虚拟币
 * @author Administrator
 *
 */
@RestController
@RequestMapping(value = { "/tLegalCurrencyCoin" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TLegalCurrencyCoinController extends BaseController<TLegalCurrencyCoin, TLegalCurrencyCoinDTO, TLegalCurrencyCoinVO, ITLegalCurrencyCoinService> {

	@Autowired
	private ITLegalCurrencyCoinService itLegalCurrencyCoinService;

	/**
	 * 获取法币虚拟币列表
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/20 18:49
	 * @return
	 */
	@GetMapping("/legalCurrency_coins")
	public ReturnDTO<Object> listLegalCurrencyCoins() {
		return ReturnDTO.ok(itLegalCurrencyCoinService.listLegalCurrencyCoins());
	}

}

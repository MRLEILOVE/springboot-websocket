package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.currency.dao.ITCurrencyTradeDAO;
import com.bittrade.pojo.dto.TCurrencyTradeDTO;
import com.bittrade.pojo.vo.TCurrencyTradeVO;
import com.bittrade.pojo.model.TCurrencyTrade;
import com.bittrade.currency.api.service.ITCurrencyTradeService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tCurrencyTrade" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TCurrencyTradeController extends BaseController<TCurrencyTrade, TCurrencyTradeDTO, TCurrencyTradeVO, ITCurrencyTradeDAO, ITCurrencyTradeService> {
	
}
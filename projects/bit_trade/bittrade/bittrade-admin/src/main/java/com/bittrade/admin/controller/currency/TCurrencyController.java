package com.bittrade.admin.controller.currency;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.admin.service.currency.ITCurrencyService;
import com.bittrade.pojo.dto.TCurrencyDTO;
import com.bittrade.pojo.vo.TCurrencyVO;
import com.bittrade.pojo.model.TCurrency;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tCurrency" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TCurrencyController extends BaseController<TCurrency, TCurrencyDTO, TCurrencyVO, ITCurrencyService> {
	
}

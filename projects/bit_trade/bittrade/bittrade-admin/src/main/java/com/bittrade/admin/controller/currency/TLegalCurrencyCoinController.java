package com.bittrade.admin.controller.currency;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.admin.service.currency.ITLegalCurrencyCoinService;
import com.bittrade.pojo.dto.TLegalCurrencyCoinDTO;
import com.bittrade.pojo.vo.TLegalCurrencyCoinVO;
import com.bittrade.pojo.model.TLegalCurrencyCoin;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tLegalCurrencyCoin" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TLegalCurrencyCoinController extends BaseController<TLegalCurrencyCoin, TLegalCurrencyCoinDTO, TLegalCurrencyCoinVO, ITLegalCurrencyCoinService> {
	
}

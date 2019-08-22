package com.bittrade.admin.controller.currency;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.admin.service.currency.ITLegalCurrencyAccountService;
import com.bittrade.pojo.dto.TLegalCurrencyAccountDTO;
import com.bittrade.pojo.vo.TLegalCurrencyAccountVO;
import com.bittrade.pojo.model.TLegalCurrencyAccount;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tLegalCurrencyAccount" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TLegalCurrencyAccountController extends BaseController<TLegalCurrencyAccount, TLegalCurrencyAccountDTO, TLegalCurrencyAccountVO, ITLegalCurrencyAccountService> {
	
}

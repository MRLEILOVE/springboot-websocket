package com.bittrade.admin.controller.currency;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.admin.service.currency.ITCurrencyOptionalService;
import com.bittrade.pojo.dto.TCurrencyOptionalDTO;
import com.bittrade.pojo.vo.TCurrencyOptionalVO;
import com.bittrade.pojo.model.TCurrencyOptional;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tCurrencyOptional" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TCurrencyOptionalController extends BaseController<TCurrencyOptional, TCurrencyOptionalDTO, TCurrencyOptionalVO, ITCurrencyOptionalService> {
	
}

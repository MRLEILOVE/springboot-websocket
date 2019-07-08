package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.currency.dao.ITCurrencyAddressDAO;
import com.bittrade.pojo.dto.TCurrencyAddressDTO;
import com.bittrade.pojo.vo.TCurrencyAddressVO;
import com.bittrade.pojo.model.TCurrencyAddress;
import com.bittrade.api.service.ITCurrencyAddressService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tCurrencyAddress" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TCurrencyAddressController extends BaseController<TCurrencyAddress, TCurrencyAddressDTO, TCurrencyAddressVO, ITCurrencyAddressDAO, ITCurrencyAddressService<ITCurrencyAddressDAO>> {
	
}

package com.bittrade.currency.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.currency.api.service.ITCurrencyService;
import com.bittrade.pojo.dto.TCurrencyDTO;
import com.bittrade.pojo.model.TCurrency;
import com.bittrade.pojo.vo.TCurrencyVO;
import com.core.common.DTO.ReturnDTO;
import com.core.framework.base.controller.BaseController;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tCurrency" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TCurrencyController extends BaseController<TCurrency, TCurrencyDTO, TCurrencyVO, ITCurrencyService> {
	@Autowired
	private ITCurrencyService tCurrencyService;

	@ApiOperation(value = "查找所有法币")
	@RequestMapping(value = "/findAllLegalCurrency", method = RequestMethod.GET)
	@ResponseBody
	public ReturnDTO<List<TCurrency>> findAllLegalCurrency() {
		return ReturnDTO.ok( tCurrencyService.findAllLegalCurrency() );
	}

	@RequestMapping(value = "/ta", method = RequestMethod.GET)
	public ReturnDTO<List<TCurrency>> ta(@RequestBody TCurrencyDTO CurrencyDTO) {
		System.out.println( "CurrencyDTO.getName()=" + CurrencyDTO.getName() );
		return ReturnDTO.ok( CurrencyDTO.getName() );
	}
}

package com.bittrade.currency.controller;

import java.util.List;

import com.bittrade.pojo.vo.CurrencyTradeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bittrade.currency.api.service.ITCurrencyTradeService;
import com.bittrade.pojo.dto.TCurrencyTradeDTO;
import com.bittrade.pojo.model.TCurrencyTrade;
import com.bittrade.pojo.vo.TCurrencyTradeVO;
import com.bittrade.pojo.vo.TransactionPairVO;
import com.core.common.DTO.ReturnDTO;
import com.core.framework.base.controller.BaseController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tCurrencyTrade" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TCurrencyTradeController extends BaseController<TCurrencyTrade, TCurrencyTradeDTO, TCurrencyTradeVO, ITCurrencyTradeService> {
	@Autowired
	private ITCurrencyTradeService tCurrencyTradeService;

	/**
	 * 根据法币id查找交易对
	 */
	@ApiOperation(value = "根据法币id查找交易对", notes = "传法币的id")
	@RequestMapping(value = "/findTradeByCurrencyId2/{currencyId2}/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public ReturnDTO<List<TransactionPairVO>> findTradeByCurrencyId2(@PathVariable("currencyId2") String currencyId2,
			@ApiParam(required = false) @PathVariable(value = "userId", required = false) String userId) {
		return ReturnDTO.ok( tCurrencyTradeService.findTradeByCurrencyId2( currencyId2, userId ) );
	}

	@ApiOperation(value = "刚点进币币页面，获取交易对信息", notes = "刚点进币币页面，获取交易对信息")
	@PostMapping(value = "/queryCurrencyTradeAtFirst")
	@ResponseBody
	public ReturnDTO<CurrencyTradeVO> queryCurrencyTradeAtFirst(@RequestBody TCurrencyTradeDTO dto) {
		return ReturnDTO.ok( tCurrencyTradeService.queryCurrencyTradeAtFirst(dto.getId()) );
	}


}

package com.bittrade.currency.controller;

import java.util.List;

import com.bittrade.common.utils.RedisKeyUtil;
import com.bittrade.pojo.vo.QueryKLineVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bittrade.currency.api.service.ITCurrencyService;
import com.bittrade.pojo.dto.TCurrencyDTO;
import com.bittrade.pojo.model.TCurrency;
import com.bittrade.pojo.vo.TCurrencyVO;
import com.core.common.DTO.ReturnDTO;
import com.core.framework.base.controller.BaseController;

import io.swagger.annotations.ApiOperation;
import redis.clients.jedis.JedisCluster;

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
	@Autowired
	private JedisCluster jedisCluster;

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

	@ApiOperation(value = "获取汇率", notes = "获取汇率")
	@GetMapping(value = "/getRate/{legalCurrencyId}")
	@ResponseBody
	public ReturnDTO<QueryKLineVO> getRate(@Param ("法币id")@PathVariable("legalCurrencyId")String legalCurrencyId) {
		TCurrency tCurrency = tCurrencyService.getByPK(legalCurrencyId);
		if(tCurrency == null){
			ReturnDTO.error("不存在该法币币种");
		}
		if("USDT".equals(tCurrency.getName())){
			return ReturnDTO.ok(jedisCluster.get(RedisKeyUtil.USD_TO_CNY_RATE_KEY));
		}
		return ReturnDTO.error("获取法币汇率失败");
	}
}

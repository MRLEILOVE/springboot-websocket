package com.bittrade.currency.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.common.utils.RedisKeyUtil;
import com.bittrade.currency.api.service.ITCurrencyService;
import com.bittrade.currency.feign.IBizAuthService;
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
	@Autowired
	private IBizAuthService bizAuthService;

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
	public ReturnDTO<String> getRate(@Param ("法币id")@PathVariable("legalCurrencyId")String legalCurrencyId) {
		TCurrency tCurrency = tCurrencyService.getByPK(legalCurrencyId);
		if(tCurrency == null){
			ReturnDTO.error("不存在该法币币种");
		}
		if("USDT".equals(tCurrency.getName())){
			return ReturnDTO.ok(jedisCluster.get(RedisKeyUtil.USD_TO_CNY_RATE_KEY));
		}
		return ReturnDTO.error("获取法币汇率失败");
	}

	@ApiOperation(value = "Feign调用")
	@RequestMapping(value = "/feignCall", method = RequestMethod.GET)
	@ResponseBody
	public ReturnDTO<Object> feignCall(Integer type) {
		System.out.println( "type=" + type );
		ReturnDTO<Object> returnDTO = ReturnDTO.error( "" + bizAuthService.list(type) );
		System.out.println( "returnDTO=" + returnDTO );
		return returnDTO;
	}
}

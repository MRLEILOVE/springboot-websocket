package com.bittrade.currency.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.bittrade.common.utils.RedisKeyUtil;
import com.bittrade.currency.api.service.ITCurrencyService;
import com.bittrade.currency.feign.IBizService;
import com.bittrade.currency.feign.IZuulService;
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
	private IBizService bizAuthService;
	@Autowired
	private IZuulService zuulService;

	@ApiOperation(value = "查找所有法币")
	@RequestMapping(value = "/findAllLegalCurrency", method = RequestMethod.GET)
	@ResponseBody
	public ReturnDTO<List<TCurrency>> findAllLegalCurrency() {
		return ReturnDTO.ok( tCurrencyService.findAllLegalCurrency() );
	}

	@ApiOperation(value = "查找所有可用币种")
	@RequestMapping(value = "/findUsableCurrency", method = RequestMethod.POST)
	@ResponseBody
	public List<String> findUsableCurrency() {
		return tCurrencyService.findUsableCurrency();
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
	@RequestMapping(value = "/feignCall")
	@ResponseBody
	public ReturnDTO<Object> feignCall(@RequestBody JSONObject objJSON, HttpServletRequest req) {
		try {
			HttpHeaders header = new HttpHeaders();
			header.add( HttpHeaders.AUTHORIZATION, req.getHeader( HttpHeaders.AUTHORIZATION ) );
			HttpEntity<JSONObject> httpEntity = new HttpEntity<JSONObject>(objJSON, header);
			String str_ret = restTemplate.postForEntity( "http://jdcloud-gateway:9000/biz/contractMicro/list1?str=123", httpEntity, String.class ).getBody();
			System.out.println( "str_ret=" + str_ret );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String str_ret1 = bizAuthService.list1();
		System.out.println("str_ret1=" + str_ret1);
		ReturnDTO<Object> returnDTO = ReturnDTO.ok( str_ret1 );
		String str_ret2 = zuulService.list1();
		System.out.println( "str_ret2=" + str_ret2 );
		
		return returnDTO;
	}
//	RestTemplate restTemplate = new RestTemplate();
	@Autowired RestTemplate restTemplate;
}

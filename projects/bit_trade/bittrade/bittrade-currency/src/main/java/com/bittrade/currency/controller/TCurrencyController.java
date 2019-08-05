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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.bittrade.common.utils.RedisKeyUtil;
import com.bittrade.currency.api.service.ITCurrencyService;
import com.bittrade.currency.feign.IBizService;
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
	public ReturnDTO<Object> feignCall(Map<String, Object> map, HttpServletRequest req) {
		try {
			HttpHeaders header = new HttpHeaders();
			header.add( HttpHeaders.AUTHORIZATION, req.getHeader( HttpHeaders.AUTHORIZATION ) );
			HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<Map<String, Object>>(map, header);
			Object obj_ret = restTemplate.postForEntity( "http://localhost:9000/biz/contractMicro/list", httpEntity, Object.class );
			System.out.println( "obj_ret=" + obj_ret );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println( "map=" + map );
		ReturnDTO<Object> returnDTO = ReturnDTO.error( "" + bizAuthService.list(map) );
		System.out.println( "returnDTO=" + returnDTO );
		
		return returnDTO;
	}
	org.springframework.web.client.RestTemplate restTemplate = new RestTemplate();
}

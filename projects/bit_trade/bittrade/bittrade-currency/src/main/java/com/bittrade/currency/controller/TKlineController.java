package com.bittrade.currency.controller;

import java.util.List;

import com.bittrade.common.utils.RedisKeyUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bittrade.entrust.api.service.ITKlineService;
import com.bittrade.pojo.dto.QueryKLineDto;
import com.bittrade.pojo.dto.TKlineDTO;
import com.bittrade.pojo.model.TKline;
import com.bittrade.pojo.vo.QueryKLineVO;
import com.bittrade.pojo.vo.TKlineVO;
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
@RequestMapping(value = { "/tKline" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TKlineController extends BaseController<TKline, TKlineDTO, TKlineVO, ITKlineService> {

	@Reference
	private ITKlineService tKlineService;


	@ApiOperation(value = "k线查询", notes = "k线查询")
	@PostMapping(value = "/queryKLine")
	@ResponseBody
	public ReturnDTO<List<QueryKLineVO>> queryKLine(@RequestBody QueryKLineDto queryKLineDto) {
		return ReturnDTO.ok( tKlineService.queryKLine( queryKLineDto ) );
	}

	@ApiOperation(value = "根据交易对查询最新k线", notes = "根据交易对id查询最新k线")
	@GetMapping(value = "/queryKLineBySymbol/{currencyTradeId}")
	@ResponseBody
	public ReturnDTO<QueryKLineVO> queryKLineBySymbol(@Param ("交易对id")@PathVariable("currencyTradeId") String currencyTradeId) {
		return ReturnDTO.ok( tKlineService.queryKLineBySymbol(currencyTradeId) );
	}

}

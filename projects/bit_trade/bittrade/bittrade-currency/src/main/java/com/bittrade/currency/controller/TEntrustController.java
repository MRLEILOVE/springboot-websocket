package com.bittrade.currency.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bittrade.entrust.api.service.ITEntrustService;
import com.bittrade.pojo.dto.DealDTO;
import com.bittrade.pojo.dto.TEntrustDTO;
import com.bittrade.pojo.model.TEntrust;
import com.bittrade.pojo.vo.TEntrustInfoVO;
import com.bittrade.pojo.vo.TEntrustVO;
import com.core.framework.DTO.ReturnDTO;
import com.core.framework.base.controller.BaseController;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tEntrust" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TEntrustController extends BaseController<TEntrust, TEntrustDTO, TEntrustVO, ITEntrustService> {
	@Reference
	private ITEntrustService entrustService;

	@ApiOperation(value = "查询用户当前委托")
	@GetMapping(value = "/queryPresentEntrustByUserId/{userId}")
	@ResponseBody
	public ReturnDTO<List<TEntrustVO>> queryPresentEntrustByUserId(@PathVariable("userId") String userId) {
		try {
			return ReturnDTO.ok( entrustService.queryPresentEntrustByUserId( userId ) );
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnDTO.error( "服务器异常" );
		}

	}

	@ApiOperation(value = "查询用户历史委托")
	@GetMapping(value = "/queryHistoryEntrustByUserId/{userId}")
	@ResponseBody
	public ReturnDTO<List<TEntrustVO>> queryHistoryEntrustByUserId(@PathVariable("userId") String userId) {
		try {
			return ReturnDTO.ok( entrustService.queryHistoryEntrustByUserId( userId ) );
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnDTO.error( "服务器异常" );
		}

	}

	@ApiOperation(value = "买/卖交易对")
	@PostMapping(value = "/deal")
	@ResponseBody
	public ReturnDTO<String> queryDealEntrustByUserId(@RequestBody DealDTO dealDTO) {
		// 1.校验参数
		String price = dealDTO.getPrice(); // 单价
		String count = dealDTO.getCount(); // 数量
		String amount = dealDTO.getAmount(); // 总价
		if (price == null || Double.parseDouble( price ) <= 0.0) {
			return ReturnDTO.error( "价格异常" );
		}
		if (count == null || Double.parseDouble( count ) <= 0) {
			return ReturnDTO.error( "数量异常" );
		}
		if (amount == null || Double.parseDouble( amount ) <= 0.0) {
			return ReturnDTO.error( "总价异常" );
		}
		if (StringUtils.isEmpty( dealDTO.getCurrencyTradeId() )) {
			return ReturnDTO.error( "交易对id为空" );
		}
		if (StringUtils.isEmpty( dealDTO.getEntrustDirection() )) {
			return ReturnDTO.error( "买卖方向为空" );
		}
		if (StringUtils.isEmpty( dealDTO.getUserId() )) {
			return ReturnDTO.error( "用户id为空" );
		}
		
		return entrustService.deal( dealDTO );
	}

	@ApiOperation(value = "查询用户的委托单成交明细")
	@RequestMapping(value = "/queryEntrustInfoByUserId/{userId}/{entrustId}", method = RequestMethod.GET)
	@ResponseBody
	public TEntrustInfoVO queryEntrustInfoByUserId(@PathVariable("userId") String userId, @PathVariable("entrustId") String entrustId) {
		return entrustService.queryEntrustInfoByUserId( userId, entrustId );
	}

	@ApiOperation(value = "用户撤单")
	@RequestMapping(value = "/killOrder/{entrustId}", method = RequestMethod.GET)
	@ResponseBody
	public ReturnDTO<Object> killOrder(@PathVariable("entrustId") String entrustId) {
		return entrustService.killOrder( entrustId );
	}
}

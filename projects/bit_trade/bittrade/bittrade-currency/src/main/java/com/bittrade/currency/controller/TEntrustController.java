package com.bittrade.currency.controller;

import java.util.List;

import com.core.framework.DTO.PageDTO;
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
import com.bittrade.common.enums.EntrustDirectionEnumer;
import com.bittrade.common.enums.EntrustTypeEnumer;
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
	@GetMapping(value = "/queryPresentEntrustByUserId")
	@ResponseBody
	public ReturnDTO<PageDTO<TEntrust>> queryPresentEntrustByUserId(TEntrust ent) {
		try {
			ent.in(TEntrust.FieldNames.STATUS, new Object[] { 1, 2 });
			PageDTO<TEntrust> tEntrustPageDTO = entrustService.getsByPagination(ent);
			if(tEntrustPageDTO != null && tEntrustPageDTO.getData() != null && tEntrustPageDTO.getData().size() > 0){
				tEntrustPageDTO.getData().stream().forEach(x ->{
					//给前端计算好成加量（借用leftCount返回）
					x.setLeftCount(x.getCount().subtract(x.getLeftCount()));
				});
			}
			return ReturnDTO.ok(tEntrustPageDTO);
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnDTO.error( "服务器异常" );
		}

	}

	@ApiOperation(value = "查询用户历史委托")
	@GetMapping(value = "/queryHistoryEntrustByUserId")
	@ResponseBody
	public ReturnDTO<PageDTO<TEntrust>> queryHistoryEntrustByUserId(TEntrust ent) {
		try {
			PageDTO<TEntrust> tEntrustPageDTO = entrustService.getsByPagination(ent);
			if(tEntrustPageDTO != null && tEntrustPageDTO.getData() != null && tEntrustPageDTO.getData().size() > 0){
				tEntrustPageDTO.getData().stream().forEach(x ->{
					//给前端计算好成加量（借用leftCount返回）
					x.setLeftCount(x.getCount().subtract(x.getLeftCount()));
				});
			}
			return ReturnDTO.ok(tEntrustPageDTO);
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnDTO.error( "服务器异常" );
		}

	}

	@ApiOperation(value = "买/卖交易对")
	@PostMapping(value = "/deal")
	@ResponseBody
	public ReturnDTO<String> deal(@RequestBody DealDTO dealDTO) {
		// 1.校验参数
		if (StringUtils.isEmpty( dealDTO.getUserId() )) {
			return ReturnDTO.error( "用户id为空" );
		}
		if (StringUtils.isEmpty( dealDTO.getCurrencyTradeId() )) {
			return ReturnDTO.error( "交易对id为空" );
		}
		if (StringUtils.isEmpty( dealDTO.getEntrustType() )) {
			return ReturnDTO.error( "委托类型为空" );
		}
		if (EntrustTypeEnumer.getEnumer( dealDTO.getEntrustType() ) == null) {
			return ReturnDTO.error( "委托类型错误" );
		}
		if (StringUtils.isEmpty( dealDTO.getEntrustDirection() )) {
			return ReturnDTO.error( "买卖方向为空" );
		}
		if (EntrustDirectionEnumer.getEnumer( dealDTO.getEntrustDirection() ) == null) {
			return ReturnDTO.error( "买卖方向错误" );
		}
		if (dealDTO.getEntrustType() == EntrustTypeEnumer.LIMIT.getCode()) {
			String price = dealDTO.getPrice();
			if (price == null || Double.parseDouble( price ) <= 0.0) {
				return ReturnDTO.error( "价格异常" );
			}
		}
		String count = dealDTO.getCount();
		if (count == null || Double.parseDouble( count ) <= 0) {
			return ReturnDTO.error( "数量异常" );
		}

		return entrustService.deal( dealDTO );
	}

	@ApiOperation(value = "查询用户的委托单成交明细")
	@RequestMapping(value = "/queryEntrustInfoByUserId/{userId}/{entrustId}", method = RequestMethod.GET)
	@ResponseBody
	public ReturnDTO<TEntrustInfoVO> queryEntrustInfoByUserId(@PathVariable("userId") String userId, @PathVariable("entrustId") String entrustId) {
		TEntrustInfoVO infoVO = entrustService.queryEntrustInfoByUserId(userId, entrustId);
		if(infoVO != null){
			//计算完成数量
			infoVO.setCompletedCount(infoVO.getCount().subtract(infoVO.getLeftCount()));
		}
		return ReturnDTO.ok(infoVO);
	}

	@ApiOperation(value = "用户撤单")
	@RequestMapping(value = "/killOrder/{entrustId}", method = RequestMethod.POST)
	@ResponseBody
	public ReturnDTO<Object> killOrder(@PathVariable("entrustId") String entrustId) {
		return entrustService.killOrder( entrustId );
	}
}

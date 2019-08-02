package com.bittrade.currency.controller;

import java.math.BigDecimal;

import com.bittrade.common.utils.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.bittrade.common.enums.EntrustStatusEnumer;
import com.bittrade.common.enums.EntrustTypeEnumer;
import com.bittrade.entrust.api.service.ITEntrustService;
import com.bittrade.pojo.dto.DealDTO;
import com.bittrade.pojo.dto.TEntrustDTO;
import com.bittrade.pojo.model.TEntrust;
import com.bittrade.pojo.vo.TEntrustInfoVO;
import com.bittrade.pojo.vo.TEntrustVO;
import com.core.common.DTO.PageDTO;
import com.core.common.DTO.ReturnDTO;
import com.core.common.constant.ICompareResultConstant;
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
@RequestMapping(value = { "/tEntrust" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TEntrustController extends BaseController<TEntrust, TEntrustDTO, TEntrustVO, ITEntrustService> {
	@Reference
	private ITEntrustService entrustService;
	@Autowired
	private JedisCluster jedisCluster;

	@ApiOperation(value = "查询用户当前委托")
	@GetMapping(value = "/queryPresentEntrustByUserId")
	@ResponseBody
	public ReturnDTO<PageDTO<TEntrust>> queryPresentEntrustByUserId(TEntrust ent) {
		ent.in( TEntrust.FieldNames.STATUS, new Object[] { EntrustStatusEnumer.UNFINISH.getCode(), EntrustStatusEnumer.PART_FINISH.getCode() } );
		PageDTO<TEntrust> tEntrustPageDTO = entrustService.getsByPagination( ent );
		if (tEntrustPageDTO != null && tEntrustPageDTO.getData() != null && tEntrustPageDTO.getData().size() > 0) {
			tEntrustPageDTO.getData().stream().forEach( x -> {
				// 给前端计算好成交量（借用leftCount返回）
				x.setLeftCount( x.getCount().subtract( x.getLeftCount() ) );
			} );
		}
		return ReturnDTO.ok( tEntrustPageDTO );
	}

	@ApiOperation(value = "查询用户历史委托")
	@GetMapping(value = "/queryHistoryEntrustByUserId")
	@ResponseBody
	public ReturnDTO<PageDTO<TEntrust>> queryHistoryEntrustByUserId(TEntrust ent) {
		PageDTO<TEntrust> tEntrustPageDTO = entrustService.getsByPagination( ent );
		if (tEntrustPageDTO != null && tEntrustPageDTO.getData() != null && tEntrustPageDTO.getData().size() > 0) {
			tEntrustPageDTO.getData().stream().forEach( x -> {
				// 给前端计算好成交量（借用leftCount返回）
				x.setLeftCount( x.getCount().subtract( x.getLeftCount() ) );
			} );
		}
		return ReturnDTO.ok( tEntrustPageDTO );
	}

	@ApiOperation(value = "买/卖交易对")
	@PostMapping(value = "/deal")
	@ResponseBody
	public ReturnDTO<String> deal(@RequestBody DealDTO dealDTO) {
		// 1.校验参数
		// if (dealDTO == null) {
		// return ReturnDTO.error( "参数为空" );
		// }
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

		// 交易规则值的校验
		if (dealDTO.getEntrustType() == EntrustTypeEnumer.LIMIT.getCode()) {
			String price = dealDTO.getPrice();
			if (price == null || new BigDecimal( price ).compareTo( BigDecimal.ZERO ) <= ICompareResultConstant.EQUAL) {
				return ReturnDTO.error( "价格为空或异常" );
			}
		}
		if (dealDTO.getEntrustType() == EntrustTypeEnumer.MARKET.getCode() && dealDTO.getEntrustDirection() == EntrustDirectionEnumer.BUY.getCode()) {
			String amount = dealDTO.getAmount();
			if (amount == null || new BigDecimal( amount ).compareTo( BigDecimal.ZERO ) <= ICompareResultConstant.EQUAL) {
				return ReturnDTO.error( "交易额为空或异常" );
			}
		} else {
			String count = dealDTO.getCount();
			if (count == null || new BigDecimal( count ).compareTo( BigDecimal.ZERO ) <= ICompareResultConstant.EQUAL) {
				return ReturnDTO.error( "数量为空或异常" );
			}
		}

		//防重复提交检验（3秒）
		String key = RedisKeyUtil.BUYING_AND_SELLING + dealDTO.getUserId();
		if(jedisCluster.get( key ) != null){
			return ReturnDTO.error( "下单过于频繁，请稍后再试");
		}
		jedisCluster.setex(key,3,"用户id ：" + dealDTO.getUserId());
		return entrustService.deal( dealDTO );
	}

	@ApiOperation(value = "查询用户的委托单成交明细")
	@RequestMapping(value = "/queryEntrustInfoByUserId/{userId}/{entrustId}", method = RequestMethod.GET)
	@ResponseBody
	public ReturnDTO<TEntrustInfoVO> queryEntrustInfoByUserId(@PathVariable("userId") String userId, @PathVariable("entrustId") String entrustId) {
		TEntrustInfoVO infoVO = entrustService.queryEntrustInfoByUserId( userId, entrustId );
		if (infoVO != null) {
			// 计算完成数量
			infoVO.setCompletedCount( infoVO.getCount().subtract( infoVO.getLeftCount() ) );
		}
		return ReturnDTO.ok( infoVO );
	}

	@ApiOperation(value = "用户撤单")
	@RequestMapping(value = "/killOrder/{entrustId}", method = RequestMethod.POST)
	@ResponseBody
	public ReturnDTO<Object> killOrder(@PathVariable("entrustId") String entrustId) {
		return entrustService.killOrder( entrustId );
	}
}

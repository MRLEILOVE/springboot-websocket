package com.bittrade.entrust.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.bittrade.__default.service.impl.DefaultTEntrustServiceImpl;
import com.bittrade.common.constant.IConstant;
import com.bittrade.common.enums.EntrustDirectionEnumer;
import com.bittrade.common.enums.EntrustStatusEnumer;
import com.bittrade.currency.api.service.ITCurrencyTradeService;
import com.bittrade.currency.api.service.ITWalletService;
import com.bittrade.entrust.api.service.ITEntrustService;
import com.bittrade.entrust.dao.ITEntrustDAO;
import com.bittrade.pojo.dto.DealDTO;
import com.bittrade.pojo.dto.TEntrustDTO;
import com.bittrade.pojo.model.TCurrencyTrade;
import com.bittrade.pojo.model.TEntrust;
import com.bittrade.pojo.model.TWallet;
import com.bittrade.pojo.vo.TEntrustInfoVO;
import com.bittrade.pojo.vo.TEntrustVO;
import com.core.framework.DTO.ReturnDTO;
import com.core.tool.SnowFlake;

/**
 * 
 * @author Administrator
 *
 */
@Service
@Component
public class TEntrustServiceImpl extends DefaultTEntrustServiceImpl<ITEntrustDAO, TEntrust, TEntrustDTO, TEntrustVO> implements ITEntrustService {

	@Autowired
	private ITEntrustDAO		entrustDAO;

	@Reference
	private ITCurrencyTradeService currencyTradeService;
	@Reference
	private ITWalletService walletService;

	private static final SnowFlake SNOW_FLAKE__ENTRUST = new SnowFlake(1, 1);

	/**
	 * 查询用户当前委托
	 */
	@Override
	public List<TEntrustVO> queryPresentEntrustByUserId(String userId) {
		return entrustDAO.queryPresentEntrustByUserId( userId );
	}

	/**
	 * 查询用户历史委托
	 */
	@Override
	public List<TEntrustVO> queryHistoryEntrustByUserId(String userId) {
		return entrustDAO.queryHistoryEntrustByUserId( userId );
	}

	/**
	 * 买/卖交易对
	 */
	@Override
	public ReturnDTO<String> deal(DealDTO dealDTO) {
		TCurrencyTrade currencyTrade = currencyTradeService.getByPK( dealDTO.getCurrencyTradeId() );
		if (currencyTrade == null) {
			return ReturnDTO.error( "交易对不存在" );
		}

		String[] split_price = dealDTO.getPrice().split( "." );
		if (split_price != null && split_price.length == 2) {
			int length = split_price[ 1 ].length();
			if (length > currencyTrade.getPriceDecimalDigits()) {
				return ReturnDTO.error( "单价小数位过长" );
			}
		}

		String[] split_count = dealDTO.getCount().split( "." );
		if (split_count != null && split_count.length == 2) {
			int length = split_count[ 1 ].length();
			if (length > currencyTrade.getCountDecimalDigits()) {
				return ReturnDTO.error( "数量小数位过长" );
			}
		}

		BigDecimal bd_count = new BigDecimal( dealDTO.getCount() );
		BigDecimal bd_price = new BigDecimal( dealDTO.getPrice() );
		BigDecimal bd_amount = new BigDecimal( dealDTO.getAmount() );

		if (bd_count.compareTo( currencyTrade.getMinBuyCount() ) == -1) {
			return ReturnDTO.error( "数量低于最小可买/可卖数量" );
		}
		if (bd_price.compareTo( currencyTrade.getMinBuyPrice() ) == -1) {
			return ReturnDTO.error( "单价低于最小可买/可卖单价" );
		}
		if (bd_amount.compareTo( currencyTrade.getMinBuyAmount() ) == -1) {
			return ReturnDTO.error( "总价低于最小可买/可卖总价" );
		}
		if (bd_count.compareTo( currencyTrade.getMaxBuyCount() ) == 1) {
			return ReturnDTO.error( "数量高于最大可买/可卖数量" );
		}
		if (bd_price.compareTo( currencyTrade.getMaxBuyPrice() ) == 1) {
			return ReturnDTO.error( "单价高于最大可买/可卖单价" );
		}
		if (bd_amount.compareTo( currencyTrade.getMaxBuyAmount() ) == 1) {
			return ReturnDTO.error( "总价高于最大可买/可卖总价" );
		}

		if (currencyTrade.getStatus() != 1) {
			return ReturnDTO.error( "交易对状态不可用" );
		}

		// 2.校验用户钱包
		TWallet queryWallet = new TWallet();
		queryWallet.setUserId( dealDTO.getUserId() );
		if (EntrustDirectionEnumer.BUY.getCode() == dealDTO.getEntrustDirection()) {
			// 买入
			// 检验法币钱包数量
			queryWallet.setCurrencyId( currencyTrade.getCurrencyId2() );
		} else {
			// 卖出
			// 检验货币钱包数量
			queryWallet.setCurrencyId( currencyTrade.getCurrencyId1() );
		}
		TWallet tWallet = walletService.getBy( queryWallet );
		if (tWallet == null) {
			return ReturnDTO.error( "用户钱包不存在" );
		}
		BigDecimal total = tWallet.getTotal();
		if (total == null || total.compareTo( bd_amount ) == -1) {
			return ReturnDTO.error( "用户钱包余额不足" );
		}
		
		// 入库委托。
		TEntrust entrust = new TEntrust();
		entrust.setId(SNOW_FLAKE__ENTRUST.nextId());
		entrust.setUserId( dealDTO.getUserId() );
		entrust.setCurrencyTradeId( dealDTO.getCurrencyTradeId() );
		// 有待续
		entrust.setPrice( bd_price );
		entrust.setCount( bd_count );
		{
			if (entrust.getPrice() != null) {
				entrust.setPrice(entrust.getPrice().setScale(IConstant.PRICE_DECIMAL_LENGTH, BigDecimal.ROUND_HALF_DOWN));
				entrust.setAmount(entrust.getPrice().multiply(entrust.getCount()).setScale(IConstant.AMOUNT_DECIMAL_LENGTH, BigDecimal.ROUND_HALF_DOWN));
			}
			entrust.setCount(entrust.getCount().setScale(IConstant.COUNT_DECIMAL_LENGTH, BigDecimal.ROUND_HALF_DOWN));
		}
		entrust.setSuccessAmount(BigDecimal.ZERO);
		entrust.setLeftCount(entrust.getCount());
		entrust.setStatus(EntrustStatusEnumer.UNFINISH.getCode());
		add(entrust);
		
		// 修改钱包。
//		walletService

		// #TODO 调用接口
		return ReturnDTO.ok( "" );
	}

	/**
	 * 查询用户的委托单成交明细
	 */
	@Override
	public TEntrustInfoVO queryEntrustInfoByUserId(String userId, String entrustId) {
		return entrustDAO.queryEntrustInfoByUserId( userId, entrustId );
	}

	/**
	 * 用户撤单
	 */
	@Override
	public ReturnDTO<Object> killOrder(String entrustId) {
		// 获取委托交易表
		TEntrust tEntrust = entrustDAO.selectById( entrustId );
		if (tEntrust == null) {
			return ReturnDTO.error( "交易单不存在" );
		}
		if (EntrustStatusEnumer.FINISH.getCode() == tEntrust.getStatus()) {
			return ReturnDTO.error( "交易已经完成，不可撤单" );
		}
		// 撤单
		int result = entrustDAO.killOrder( tEntrust );
		if (result == 0) {
			return ReturnDTO.error( "撤单失败" );
		} else {
			return ReturnDTO.ok( "撤单成功" );
		}
	}

	@Override
	public void updateOnMatch(BigDecimal successAmount, BigDecimal leftCount, int status, long ID) {
		entrustDAO.updateOnMatch( successAmount, leftCount, status, ID );
	}

	@Override
	public String testPrm(QueryWrapper<TEntrust> qw) {
		System.out.println( "---------------------qw=" + qw );
		return "--==";
	}

	@Override
	public String testPrm_2(MergeSegments ms) {
		System.out.println( "---------------------ms=" + ms );
		return "testPrm_2 --==";
	}

}

package com.bittrade.entrust.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.bittrade.__default.service.impl.DefaultTEntrustServiceImpl;
import com.bittrade.common.constant.IConstant;
import com.bittrade.common.enums.EntrustDirectionEnumer;
import com.bittrade.common.enums.EntrustStatusEnumer;
import com.bittrade.common.enums.EntrustTypeEnumer;
import com.bittrade.common.enums.StatusEnumer;
import com.bittrade.currency.api.service.ITCurrencyTradeService;
import com.bittrade.currency.api.service.ITWalletService;
import com.bittrade.entrust.api.service.IMakeAMatchService;
import com.bittrade.entrust.api.service.ITEntrustService;
import com.bittrade.entrust.dao.ITEntrustDAO;
import com.bittrade.pojo.dto.DealDTO;
import com.bittrade.pojo.dto.TEntrustDTO;
import com.bittrade.pojo.model.TCurrencyTrade;
import com.bittrade.pojo.model.TEntrust;
import com.bittrade.pojo.model.TWallet;
import com.bittrade.pojo.vo.TEntrustInfoVO;
import com.bittrade.pojo.vo.TEntrustVO;
import com.core.common.DTO.ReturnDTO;
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
	private ITEntrustDAO					entrustDAO;

	@Reference(check=false)
	private ITCurrencyTradeService			currencyTradeService;
	@Reference
	private ITWalletService					walletService;
	@Autowired
	private IMakeAMatchService				makeAMatchService;

	private static final SnowFlake			SNOW_FLAKE__ENTRUST	= new SnowFlake( 1, 1 );
	private static final ExecutorService	ES					= Executors.newFixedThreadPool( 50 );

	@Override
	public int add(TEntrust entrust) {
		entrust.setId( SNOW_FLAKE__ENTRUST.nextId() );
		{
			if (
					entrust.getEntrustType() == EntrustTypeEnumer.LIMIT.getCode()
					||
					entrust.getEntrustDirection() == EntrustDirectionEnumer.SELL.getCode()
					) {
				entrust.setCount( entrust.getCount().setScale( IConstant.COUNT_DECIMAL_LENGTH, BigDecimal.ROUND_HALF_DOWN ) ); // 设置精度
				entrust.setLeftCount( entrust.getCount() );
			}
			if (entrust.getEntrustType() == EntrustTypeEnumer.LIMIT.getCode()) {
				entrust.setPrice( entrust.getPrice().setScale(IConstant.PRICE_DECIMAL_LENGTH, BigDecimal.ROUND_HALF_DOWN) ); // 设置精度
				entrust.setAmount( entrust.getPrice().multiply( entrust.getCount() ).setScale( IConstant.AMOUNT_DECIMAL_LENGTH, BigDecimal.ROUND_HALF_DOWN ) );
			}
		}
		entrust.setSuccessAmount( BigDecimal.ZERO );
		entrust.setStatus( EntrustStatusEnumer.UNFINISH.getCode() );
		entrust.setVersion( 0 );
		entrust.setCreateTime(LocalDateTime.now());
		
		return super.add(entrust);
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
		if (currencyTrade.getStatus() != StatusEnumer.ENABLE.getCode()) {
			return ReturnDTO.error( "交易对状态不可用" );
		}

		/**
		 * <p>
		 * 也可以把这些 限市、买卖 各种情况综合起来， 按照每一种类型来分别判断一次， 这样代码会有冗余， 但是思路更清晰。
		 * <br />
		 * 货币： 物品、币 （意思）
		 * 法币： 金钱 （意思）
		 * </p>
		 * <pre>
		 * 1、限价、买：price、count单位货币，检查钱包单位需要转换成法币。
		 * 2、限价、卖：price、count单位货币，检查钱包单位不需要转换。
		 * 3、市价、买：amount单位法币，检查钱包单位不需要转换。
		 * 4、市价、卖：count单位货币，检查钱包单位不需要转换。
		 * </pre>
		 */
		BigDecimal bd_price = null;
		BigDecimal bd_count = null;
		BigDecimal bd_amount = null;
		if (dealDTO.getEntrustType() == EntrustTypeEnumer.LIMIT.getCode()) {
			String[] split_price = dealDTO.getPrice().split( "\\." );
			if (split_price != null && split_price.length == 2) {
				int length = split_price[ 1 ].length();
				if (length > currencyTrade.getPriceDecimalDigits()) {
					return ReturnDTO.error( "单价小数位过长" );
				}
			}
			bd_price = new BigDecimal( dealDTO.getPrice() );
			if (bd_price.compareTo( currencyTrade.getMinPrice() ) == -1) {
				return ReturnDTO.error( "单价低于最小可买/可卖单价" );
			}
		}
		if (
				dealDTO.getEntrustType() == EntrustTypeEnumer.LIMIT.getCode()
				||
				dealDTO.getEntrustDirection() == EntrustDirectionEnumer.SELL.getCode()
				) {
			String[] split_count = dealDTO.getCount().split( "\\." );
			if (split_count != null && split_count.length == 2) {
				int length = split_count[ 1 ].length();
				if (length > currencyTrade.getCountDecimalDigits()) {
					return ReturnDTO.error( "数量小数位过长" );
				}
			}
			bd_count = new BigDecimal( dealDTO.getCount() );
			if (bd_count.compareTo( currencyTrade.getMinCount() ) == -1) {
				return ReturnDTO.error( "数量低于最小可买/可卖数量" );
			}
		}
		// if (bd_amount.compareTo( currencyTrade.getMinAmount() ) == -1) {
		// return ReturnDTO.error( "总价低于最小可买/可卖总价" );
		// }
		// if (bd_price.compareTo( currencyTrade.getMaxPrice() ) == 1) {
		// return ReturnDTO.error( "单价高于最大可买/可卖单价" );
		// }
		// if (bd_count.compareTo( currencyTrade.getMaxCount() ) == 1) {
		// return ReturnDTO.error( "数量高于最大可买/可卖数量" );
		// }
		// if (bd_amount.compareTo( currencyTrade.getMaxAmount() ) == 1) {
		// return ReturnDTO.error( "总价高于最大可买/可卖总价" );
		// }

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
		if (EntrustDirectionEnumer.BUY.getCode() == dealDTO.getEntrustDirection()) {
			BigDecimal bd_amount_;
			if (EntrustTypeEnumer.LIMIT.getCode() == dealDTO.getEntrustType()) {
				bd_amount_ = bd_price.multiply( bd_count );
				if (total == null || total.compareTo( bd_amount_ ) == -1) {
					return ReturnDTO.error( "用户钱包余额不足" );
				}
			} else /*if (EntrustTypeEnumer.MARKET.getCode() == dealDTO.getEntrustType()) */{
				bd_amount = new BigDecimal( dealDTO.getAmount() );
				if (total == null || total.compareTo( bd_amount ) == -1) {
					return ReturnDTO.error( "用户钱包余额不足" );
				}
			}
		} else /*if (EntrustDirectionEnumer.SELL.getCode() == dealDTO.getEntrustDirection()) */{
			if (total == null || total.compareTo( bd_count ) == -1) {
				return ReturnDTO.error( "用户钱包余额不足" );
			}
		}

		// 修改钱包。 版本不对，要继续轮询？
		walletService.modifyTradeFrozen( bd_count, tWallet.getVersion(), tWallet.getId() );

		// 入库委托。
		TEntrust entrust = new TEntrust();
		entrust.setUserId( dealDTO.getUserId() );
		entrust.setCurrencyTradeId( dealDTO.getCurrencyTradeId() );
		entrust.setEntrustType( dealDTO.getEntrustType() );
		entrust.setEntrustDirection( dealDTO.getEntrustDirection() );
		entrust.setPrice( bd_price );
		entrust.setCount( bd_count );
		entrust.setAmount( bd_amount );
		add( entrust );

		ES.submit( new Callable<String>() {
			@Override
			public String call() throws Exception {
				makeAMatchService.makeAMatch( entrust );
				return null;
			}
		} );

		return ReturnDTO.ok( "委托成功" );
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
		tEntrust.setStatus(EntrustStatusEnumer.CANCEL.getCode());
		int result = entrustDAO.killOrder( tEntrust );
		if (result == 0) {
			return ReturnDTO.error( "撤单失败" );
		} else {
			return ReturnDTO.ok( "撤单成功" );
		}
	}

	@Override
	public int updateOnMatch(BigDecimal successAmount, BigDecimal leftCount, int status, LocalDateTime updateTime, long ID, int version) {
		return entrustDAO.updateOnMatch( successAmount, leftCount, status, updateTime, ID, version );
	}

}

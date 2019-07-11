package com.bittrade.batch.scheduled;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bittrade.batch.general.GeneralMethod;
import com.bittrade.currency.api.service.ITCurrencyTradeService;
import com.bittrade.currency.api.service.ITEntrustRecordService;
import com.bittrade.currency.api.service.ITWalletService;
import com.bittrade.pojo.model.TCurrencyTrade;
import com.bittrade.pojo.model.TEntrustRecord;
import com.bittrade.pojo.model.TWallet;

/**
 * 结算
 * 
 * @author zale
 *
 */
@Component
public class SettleAccount {

	// 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
	private ExecutorService			cachedThreadPool	= Executors.newFixedThreadPool( 20 );

	private static final Logger		LOG					= LoggerFactory.getLogger( SettleAccount.class );

	@Reference
	private ITEntrustRecordService	entrustRecordService;

	@Reference
	private ITCurrencyTradeService	currencyTradeService;

	@Reference
	private ITWalletService			walletService;

	//@Scheduled(cron = "0/1 * * * * ?")
	public void sellte() {
		try {
			// 1、只查一个方向的订单(卖)
			QueryWrapper<TEntrustRecord> entrustRecordQuery = new QueryWrapper<TEntrustRecord>();
			entrustRecordQuery.eq( TEntrustRecord.FieldNames.ENTRUST_DIRECTION, 1 );
			TEntrustRecord entrustRecords = entrustRecordService.getOne( entrustRecordQuery );
			long currentUserId = entrustRecords.getUserId();// 用户id
			long rivalUserId = entrustRecords.getRivalUserId();// 对手方用户id

			BigDecimal amount = entrustRecords.getAmount();
			BigDecimal count = entrustRecords.getCount();

			// 2、根据交易对Id获去币种id
			QueryWrapper<TCurrencyTrade> currencyTradeQuery = new QueryWrapper<TCurrencyTrade>();
			entrustRecordQuery.eq( TCurrencyTrade.FieldNames.ID, 1 );
			TCurrencyTrade currencyTrade = currencyTradeService.getOne( currencyTradeQuery );
			int currencyId = currencyTrade.getCurrencyId1();// 货比id
			int marketId = currencyTrade.getCurrencyId2();// 法币id

			// currentUserId-->卖currencyId，currencyId(count)↓，marketId(amout)↑
			TWallet sellCurrencyIdWallet = GeneralMethod.qryUserWallet( walletService, currentUserId, currencyId );
			TWallet sellMarketIdWallet = GeneralMethod.qryUserWallet( walletService, currentUserId, marketId );
			if (sellCurrencyIdWallet.getTradeFrozen().compareTo( count ) < 0) {
				throw new Exception( "结算异常" );
			}
			updateWallet( sellCurrencyIdWallet, count, true );// 减count
			updateWallet( sellMarketIdWallet, amount, false );// 加amount

			// rivalUserId-->买currencyId，currencyId(count)↑，marketId(amout)↓
			TWallet buyMarketIdWallet = GeneralMethod.qryUserWallet( walletService, rivalUserId, marketId );
			TWallet buyCurrencyIdWallet = GeneralMethod.qryUserWallet( walletService, rivalUserId, currencyId );
			if (buyMarketIdWallet.getTradeFrozen().compareTo( amount ) < 0) {
				throw new Exception( "结算异常" );
			}
			updateWallet( buyMarketIdWallet, amount, true );// 减amount
			updateWallet( buyCurrencyIdWallet, count, false );// 加count
		} catch (Exception e) {
			LOG.error( e.getMessage(), e );
		}
	}

	private boolean updateWallet(TWallet wallet, BigDecimal val, boolean bool) {
		TWallet updateWallet = new TWallet();// 修改的对象
		if (bool) {
			updateWallet.setTradeFrozen( wallet.getTradeFrozen().subtract( val ) );
		} else {
			updateWallet.setTotal( wallet.getTotal().add( val ) );
		}
		updateWallet.setVersion( wallet.getVersion() + 1 );

		UpdateWrapper<TWallet> updateSellMarketIdWrapper = new UpdateWrapper<TWallet>(); // 条件
		updateSellMarketIdWrapper.eq( TWallet.FieldNames.ID, wallet.getId() );
		updateSellMarketIdWrapper.eq( TWallet.FieldNames.VERSION, wallet.getVersion() );
		return walletService.update( updateWallet, updateSellMarketIdWrapper );
	}

}

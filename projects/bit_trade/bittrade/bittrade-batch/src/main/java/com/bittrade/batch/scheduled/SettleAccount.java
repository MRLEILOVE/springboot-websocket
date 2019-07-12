package com.bittrade.batch.scheduled;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bittrade.batch.general.GeneralMethod;
import com.bittrade.currency.api.service.ITCurrencyTradeService;
import com.bittrade.currency.api.service.ITWalletRecordService;
import com.bittrade.currency.api.service.ITWalletService;
import com.bittrade.entrust.api.service.ITEntrustRecordService;
import com.bittrade.pojo.model.TCurrencyTrade;
import com.bittrade.pojo.model.TEntrustRecord;
import com.bittrade.pojo.model.TWallet;
import com.bittrade.pojo.model.TWalletRecord;
import com.core.tool.SnowFlake;

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

	@Reference
	private ITWalletRecordService	walletRecordService;

	private static final SnowFlake	SNOW_FLAKE			= new SnowFlake( 1, 1 );

	//@Scheduled(cron = "0/1 * * * * ?")
	public void sellte() {
		try {
			// 1、查询卖方向的未结算的订单
			TEntrustRecord entrustRecords = new TEntrustRecord();
			entrustRecords.setEntrustDirection( 1 );
			entrustRecords = entrustRecordService.getBy( entrustRecords );

			long currentUserId = entrustRecords.getUserId();// 用户id
			long rivalUserId = entrustRecords.getRivalUserId();// 对手方用户id
			long entrustRecordId = entrustRecords.getId();
			int currencyTradeId = entrustRecords.getCurrencyTradeId();

			BigDecimal count = entrustRecords.getCount();
			BigDecimal amount = entrustRecords.getAmount();

			// 2、根据交易对Id获去币种id
			TCurrencyTrade currencyTrade = new TCurrencyTrade();
			currencyTrade.setId( currencyTradeId );
			currencyTrade = currencyTradeService.getBy( currencyTrade );
			int currencyId = currencyTrade.getCurrencyId1();// 货比id
			int marketId = currencyTrade.getCurrencyId2();// 法币id

			// 3、获取卖家币币钱包信息
			// currentUserId-->卖currencyId，currencyId(count)↓，marketId(amout)↑
			TWallet sellCurrencyIdWallet = GeneralMethod.qryUserWallet( walletService, currentUserId, currencyId );
			TWallet sellMarketIdWallet = GeneralMethod.qryUserWallet( walletService, currentUserId, marketId );
			if (sellCurrencyIdWallet.getTradeFrozen().compareTo( count ) < 0) {
				throw new Exception( "TEntrustRecord.id=" + entrustRecords.getId() + "sellte.faile,reason.userId=" + currentUserId + "currencyId="
						+ currencyId + "tradeFrozen=" + sellCurrencyIdWallet.getTradeFrozen() + "count=" + count );
			}

			// 4、更新卖家币币钱包
			updateUserWallet( sellCurrencyIdWallet, count, entrustRecordId, true );// 减count
			updateUserWallet( sellMarketIdWallet, amount, entrustRecordId, false );// 加amount

			// 5、获取对手的币币钱包信息
			// rivalUserId-->买currencyId，currencyId(count)↑，marketId(amout)↓
			TWallet buyMarketIdWallet = GeneralMethod.qryUserWallet( walletService, rivalUserId, marketId );
			TWallet buyCurrencyIdWallet = GeneralMethod.qryUserWallet( walletService, rivalUserId, currencyId );
			if (buyMarketIdWallet.getTradeFrozen().compareTo( amount ) < 0) {
				throw new Exception( "TEntrustRecord.id=" + entrustRecords.getId() + "sellte.faile,reason.userId=" + rivalUserId + "marketId="
						+ marketId + "tradeFrozen=" + buyMarketIdWallet.getTradeFrozen() + "amount=" + amount );
			}

			// 6、更新对手钱包信息
			updateUserWallet( buyMarketIdWallet, amount, entrustRecordId, true );// 减amount
			updateUserWallet( buyCurrencyIdWallet, count, entrustRecordId, false );// 加count
		} catch (Exception e) {
			LOG.error( e.getMessage(), e );
		}
	}

	private void updateUserWallet(TWallet wallet, BigDecimal val, long entrustRecordId, boolean bool) {
		TWallet updateWallet = new TWallet();// 修改的对象
		if (bool) {
			updateWallet.setTradeFrozen( wallet.getTradeFrozen().subtract( val ) );
		} else {
			updateWallet.setTotal( wallet.getTotal().add( val ) );
		}
		updateWallet.setVersion( wallet.getVersion() + 1 );
		updateWallet.setUpdateTime( new Date() );

		TWallet updateSellMarketIdWallet = new TWallet(); // 条件
		updateSellMarketIdWallet.setId( wallet.getId() );
		updateSellMarketIdWallet.setUserId( wallet.getUserId() );
		updateSellMarketIdWallet.setCurrencyId( wallet.getCurrencyId() );
		updateSellMarketIdWallet.setVersion( wallet.getVersion() );

		// 更新钱包
		int row = walletService.modifyWithSelectiveBy( updateWallet, updateSellMarketIdWallet );
		System.out.println( "row=" + row );

		// 记录钱包流水
		TWalletRecord walletRecord = new TWalletRecord();
		walletRecord.setId( SNOW_FLAKE.nextId() );
		walletRecord.setUserId( wallet.getUserId() );
		walletRecord.setCurrencyId( wallet.getCurrencyId() );
		walletRecord.setType( (byte) 3 );
		walletRecord.setEntrustRecordId( entrustRecordId );
		walletRecord.setCreateTime( new Date() );

		BigDecimal beforeAmount = wallet.getTotal().add( wallet.getTradeFrozen() ).add( wallet.getTransferFrozen() );
		walletRecord.setBeforeAmount( beforeAmount );
		if (bool) {
			walletRecord.setChangeAmount( BigDecimal.ZERO.subtract( val ) );
		} else {
			walletRecord.setChangeAmount( val );
		}
		walletRecord.setAfterAmount( beforeAmount.add( val ) );
		// 插入流水
		walletRecordService.add( walletRecord );

	}

}

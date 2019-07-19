package com.bittrade.batch.scheduled;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
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
import com.rabbitmq.client.Channel;

/**
 * 结算
 * 
 * @author zale
 *
 */
@Component
public class SettleAccount {

	private static final Logger	LOG	= LoggerFactory.getLogger( SettleAccount.class );

	@Reference
	private ITWalletService		walletService;

	// @RabbitListener(queues = IQueueConstants.QUEUE__KLINE)
	@Scheduled(cron = "0/10 * * * * ?")
	public void processMessage() { // Channel channel, Message message
		try {
			// 1、处理mq推送过来的撮合数据，进行结算
			// String msg = new String( message.getBody() );
			String msg = "{\"amount\":100,\"count\":10,\"userId\":1,\"current\":0,\"size\":0,\"rivalUserId\":2,\"id\":300012213121331322,\"currencyTradeId\":1}";
			TEntrustRecord entrustRecords = JSONObject.parseObject( msg, TEntrustRecord.class );
			walletService.modifyWalletSellte( entrustRecords );
		} catch (Exception e) {
			LOG.error( e.getMessage(), e );
		}
	}

	// private void walletSettle(TEntrustRecord entrustRecords) throws Exception
	// {
	// long currentUserId = entrustRecords.getUserId();// 用户id
	// long rivalUserId = entrustRecords.getRivalUserId();// 对手方用户id
	// long entrustRecordId = entrustRecords.getId();
	// int currencyTradeId = entrustRecords.getCurrencyTradeId();
	//
	// BigDecimal count = entrustRecords.getCount();
	// BigDecimal amount = entrustRecords.getAmount();
	//
	// // 2、根据交易对Id获去币种id
	// TCurrencyTrade currencyTrade = new TCurrencyTrade();
	// currencyTrade.setId( currencyTradeId );
	// currencyTrade = currencyTradeService.getBy( currencyTrade );
	// int currencyId = currencyTrade.getCurrencyId1();// 货比id
	// int marketId = currencyTrade.getCurrencyId2();// 法币id
	//
	// // 3、获取卖家币币钱包信息
	// // currentUserId-->卖currencyId，currencyId(count)↓，marketId(amout)↑
	// TWallet sellCurrencyIdWallet = GeneralMethod.qryUserWallet(
	// walletService, currentUserId, currencyId );
	// TWallet sellMarketIdWallet = GeneralMethod.qryUserWallet( walletService,
	// currentUserId, marketId );
	// if (sellCurrencyIdWallet.getTradeFrozen().compareTo( count ) < 0) {
	// throw new Exception( "TEntrustRecord.id=" + entrustRecords.getId() +
	// "sellte.faile,reason.userId=" + currentUserId + "currencyId="
	// + currencyId + "tradeFrozen=" + sellCurrencyIdWallet.getTradeFrozen() +
	// "count=" + count );
	// }
	//
	// // 4、更新卖家币币钱包
	// updateUserWallet( sellCurrencyIdWallet, count, entrustRecordId, true );//
	// 减count
	// updateUserWallet( sellMarketIdWallet, amount, entrustRecordId, false );//
	// 加amount
	//
	// // 5、获取对手的币币钱包信息
	// // rivalUserId-->买currencyId，currencyId(count)↑，marketId(amout)↓
	// TWallet buyMarketIdWallet = GeneralMethod.qryUserWallet( walletService,
	// rivalUserId, marketId );
	// TWallet buyCurrencyIdWallet = GeneralMethod.qryUserWallet( walletService,
	// rivalUserId, currencyId );
	// if (buyMarketIdWallet.getTradeFrozen().compareTo( amount ) < 0) {
	// throw new Exception( "TEntrustRecord.id=" + entrustRecords.getId() +
	// "sellte.faile,reason.userId=" + rivalUserId + "marketId=" + marketId
	// + "tradeFrozen=" + buyMarketIdWallet.getTradeFrozen() + "amount=" +
	// amount );
	// }
	//
	// // 6、更新对手钱包信息
	// updateUserWallet( buyMarketIdWallet, amount, entrustRecordId, true );//
	// 减amount
	// updateUserWallet( buyCurrencyIdWallet, count, entrustRecordId, false );//
	// 加count
	// }

	/**
	 * updateUserWallet:(操作用户钱包). <br/>
	 * 
	 * @author Administrator
	 * @param wallet
	 * @param val
	 * @param entrustRecordId
	 * @param bool
	 * @since JDK 1.8
	 */
	// private void updateUserWallet(TWallet wallet, BigDecimal val, long
	// entrustRecordId, boolean bool) throws Exception {
	// TWallet updateWallet = new TWallet();// 修改的对象
	// if (bool) {
	// updateWallet.setTradeFrozen( wallet.getTradeFrozen().subtract( val ) );
	// } else {
	// updateWallet.setTotal( wallet.getTotal().add( val ) );
	// }
	// updateWallet.setVersion( wallet.getVersion() + 1 );
	// updateWallet.setUpdateTime( new Date() );
	//
	// TWallet updateSellMarketIdWallet = new TWallet(); // 条件
	// updateSellMarketIdWallet.setId( wallet.getId() );
	// updateSellMarketIdWallet.setUserId( wallet.getUserId() );
	// updateSellMarketIdWallet.setCurrencyId( wallet.getCurrencyId() );
	// updateSellMarketIdWallet.setVersion( wallet.getVersion() );
	//
	// // 更新钱包
	// // int row = walletService.modifyWithSelectiveBy( updateWallet,
	// // updateSellMarketIdWallet );
	// // LOG.info( "row=" + row );
	//
	// // 记录钱包流水
	// TWalletRecord walletRecord = new TWalletRecord();
	// walletRecord.setId( SNOW_FLAKE.nextId() );
	// walletRecord.setUserId( wallet.getUserId() );
	// walletRecord.setCurrencyId( wallet.getCurrencyId() );
	// walletRecord.setType( (byte) 3 );
	// walletRecord.setEntrustRecordId( entrustRecordId );
	// walletRecord.setCreateTime( new Date() );
	//
	// BigDecimal beforeAmount = wallet.getTotal().add( wallet.getTradeFrozen()
	// ).add( wallet.getTransferFrozen() );
	// walletRecord.setBeforeAmount( beforeAmount );
	// if (bool) {
	// walletRecord.setChangeAmount( BigDecimal.ZERO.subtract( val ) );
	// } else {
	// walletRecord.setChangeAmount( val );
	// }
	// walletRecord.setAfterAmount( beforeAmount.add( val ) );
	// // 插入流水
	// walletRecordService.add( walletRecord );
	// }

}

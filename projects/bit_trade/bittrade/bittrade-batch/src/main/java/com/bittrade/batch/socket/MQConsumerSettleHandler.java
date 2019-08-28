package com.bittrade.batch.socket;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.bittrade.common.constant.IQueueConstants;
import com.bittrade.pojo.model.TEntrustRecord;
import com.common.bittrade.service.ITWalletService;
import com.rabbitmq.client.Channel;

/**
 * 结算
 * 
 * @author zale
 *
 */
@Component
public class MQConsumerSettleHandler {

	private static final Logger						LOG					= LoggerFactory.getLogger( MQConsumerSettleHandler.class );

	@Reference
	private ITWalletService							walletService;

	private ConcurrentHashMap<Long, ReentrantLock>	map					= new ConcurrentHashMap<Long, ReentrantLock>();

	private ExecutorService							cachedThreadPool	= Executors.newFixedThreadPool( 200 );

	public ReentrantLock getLock(Long userId) {
		ReentrantLock lock = null;
		if (!map.containsKey( userId )) {
			lock = new ReentrantLock();
			map.put( userId, lock );
			return lock;
		}
		return map.get( userId );
	}

	@RabbitListener(queues = IQueueConstants.QUEUE__ENTRUST_RECORD)
	public void processMessage(Channel channel, Message message) {
		cachedThreadPool.execute( () -> {
			// 1、处理mq推送过来的撮合数据，进行结算
			String msg = new String( message.getBody() );
			TEntrustRecord entrustRecords = JSONObject.parseObject( msg, TEntrustRecord.class );
			ReentrantLock lock = getLock( entrustRecords.getUserId() );
			lock.lock();// 悲观锁
			try {
				dataHandler( entrustRecords );
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		} );
	}

	private void dataHandler(TEntrustRecord entrustRecords) {
		try {
			walletService.modifyWalletSellte( entrustRecords );
		} catch (Exception e) {
			LOG.error( e.toString() );
		}
	}

}

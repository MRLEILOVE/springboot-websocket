package com.bittrade.batch.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.bittrade.common.constant.IQueueConstants;
import com.bittrade.currency.api.service.ITWalletService;
import com.bittrade.pojo.model.TEntrustRecord;
import com.rabbitmq.client.Channel;

/**
 * 结算
 * 
 * @author zale
 *
 */
@Component
public class MQConsumerSettleHandler {

	private static final Logger	LOG	= LoggerFactory.getLogger( MQConsumerSettleHandler.class );

	@Reference
	private ITWalletService		walletService;

	@RabbitListener(queues = IQueueConstants.QUEUE__ENTRUST_RECORD)
	public void processMessage(Channel channel, Message message) {
		try {
			LOG.info( "开始结算....................................................." );
			// 1、处理mq推送过来的撮合数据，进行结算
			String msg = new String( message.getBody() );
			TEntrustRecord entrustRecords = JSONObject.parseObject( msg, TEntrustRecord.class );
			walletService.modifyWalletSellte( entrustRecords );
			LOG.info( "结算成功....................................................." );
		} catch (Exception e) {
			LOG.error( e.getMessage(), e );
		}
	}

}

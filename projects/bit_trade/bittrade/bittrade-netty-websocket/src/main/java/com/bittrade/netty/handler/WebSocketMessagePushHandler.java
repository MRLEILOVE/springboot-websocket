package com.bittrade.netty.handler;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.bittrade.common.constant.IQueueConstants;
import com.rabbitmq.client.Channel;

import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

@Component
public class WebSocketMessagePushHandler {

	// @Scheduled(cron = "0/10 * * * * ?")
	public void sendMsg() {
		ConcurrentHashMap<String, ChannelGroup> concurrentHashMap = Global.concurrentHashMap;
		for (Map.Entry<String, ChannelGroup> map : concurrentHashMap.entrySet()) {
			String key = map.getKey();
			System.out.println( "**********************************************" + key );
			ChannelGroup channelGroup = map.getValue();
			TextWebSocketFrame contws = new TextWebSocketFrame( "服务端返回：" + (int) new Random().nextInt( 100 ) );
			channelGroup.writeAndFlush( contws );
		}
	}

	@RabbitListener(queues = IQueueConstants.QUEUE__KLINE + 60)
	public void processMessage60(Channel channel, Message message) {
		msgHandler( message, Global.granularitys.get( 0 ) );
	}

	@RabbitListener(queues = IQueueConstants.QUEUE__KLINE + 180)
	public void processMessage180(Channel channel, Message message) {
		msgHandler( message, Global.granularitys.get( 1 ) );
	}

	@RabbitListener(queues = IQueueConstants.QUEUE__KLINE + 300)
	public void processMessage300(Channel channel, Message message) {
		msgHandler( message, Global.granularitys.get( 2 ) );
	}

	@RabbitListener(queues = IQueueConstants.QUEUE__KLINE + 900)
	public void processMessage900(Channel channel, Message message) {
		msgHandler( message, Global.granularitys.get( 3 ) );
	}

	@RabbitListener(queues = IQueueConstants.QUEUE__KLINE + 1800)
	public void processMessage1800(Channel channel, Message message) {
		msgHandler( message, Global.granularitys.get( 4 ) );
	}

	@RabbitListener(queues = IQueueConstants.QUEUE__KLINE + 3600)
	public void processMessage3600(Channel channel, Message message) {
		msgHandler( message, Global.granularitys.get( 5 ) );
	}

	@RabbitListener(queues = IQueueConstants.QUEUE__KLINE + 7200)
	public void processMessage7200(Channel channel, Message message) {
		msgHandler( message, Global.granularitys.get( 6 ) );
	}

	@RabbitListener(queues = IQueueConstants.QUEUE__KLINE + 14400)
	public void processMessage14400(Channel channel, Message message) {
		msgHandler( message, Global.granularitys.get( 7 ) );
	}

	@RabbitListener(queues = IQueueConstants.QUEUE__KLINE + 21600)
	public void processMessage21600(Channel channel, Message message) {
		msgHandler( message, Global.granularitys.get( 8 ) );
	}

	@RabbitListener(queues = IQueueConstants.QUEUE__KLINE + 43200)
	public void processMessage43200(Channel channel, Message message) {
		msgHandler( message, Global.granularitys.get( 9 ) );
	}

	@RabbitListener(queues = IQueueConstants.QUEUE__KLINE + 86400)
	public void processMessage86400(Channel channel, Message message) {
		msgHandler( message, Global.granularitys.get( 10 ) );
	}

	@RabbitListener(queues = IQueueConstants.QUEUE__KLINE + 604800)
	public void processMessage604800(Channel channel, Message message) {
		msgHandler( message, Global.granularitys.get( 11 ) );
	}

	/**
	 * msgHandler:(消息处理). <br/>
	 * 
	 * @author Administrator
	 * @param message
	 * @param granularity
	 * @since JDK 1.8
	 */
	private void msgHandler(Message message, String granularity) {
		String context = new String( message.getBody() );
		JSONArray object = JSONArray.parseArray( context );
		String symbol = object.getString( 0 );
		String key = symbol + "_" + granularity;
		ConcurrentHashMap<String, ChannelGroup> concurrentHashMap = Global.concurrentHashMap;
		if (concurrentHashMap.size() == 0) {
			return;
		}
		ChannelGroup channelGroup = concurrentHashMap.get( key );
		if (null == channelGroup) {
			return;
		}
		TextWebSocketFrame contws = new TextWebSocketFrame( context );
		channelGroup.writeAndFlush( contws );
	}

}

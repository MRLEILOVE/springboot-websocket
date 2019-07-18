package com.bittrade.netty.handler;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.bittrade.common.constant.IQueueConstants;
import com.rabbitmq.client.Channel;

import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

@Component
public class TestJob {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Scheduled(cron = "0/10 * * * * ?")
	public void sendMsg() {
		// ConcurrentHashMap<String, ChannelGroup> concurrentHashMap =
		// Global.concurrentHashMap;
		// for (Map.Entry<String, ChannelGroup> map :
		// concurrentHashMap.entrySet()) {
		// String key = map.getKey();
		// System.out.println( "**********************************************"
		// + key );
		// ChannelGroup channelGroup = map.getValue();
		// TextWebSocketFrame contws = new TextWebSocketFrame( "zale服务端返回：" +
		// (int) new Random().nextInt( 100 ) );
		// channelGroup.writeAndFlush( contws );
		// }
		// // 群发组
		// // Global.group.writeAndFlush( contws );
		//
		// rabbitTemplate.convertAndSend( "abc", "abc" );
		// System.out.println( 1 );
		String context = "[\"BTC-USDT\",\"2019-07-18 17:00:00\",\"9783.2\",\"9787.9\",\"9778.3\",\"9782\",\"3.40246089\"]";
		JSONArray object = JSONArray.parseArray( context );
		String symbol = object.getString( 0 );
		String key = symbol + "_" + 60;
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

	@RabbitListener(queues = IQueueConstants.QUEUE__KLINE)
	public void processMessage(Channel channel, Message message) {
		//System.out.println( new String(message.getBody()) );
		String context = "[\"BTC-USDT\",\"2019-07-18 17:00:00\",\"9783.2\",\"9787.9\",\"9778.3\",\"9782\",\"3.40246089\"]";
		JSONArray object = JSONArray.parseArray( context );
		String symbol = object.getString( 0 );
		String key = symbol + "_" + 60;
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

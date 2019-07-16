package com.bittrade.netty.handler;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bittrade.common.constant.IQueueConstants;
import com.rabbitmq.client.Channel;

import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

@Component
public class TestJob {

	@Scheduled(cron = "0/10 * * * * ?")
	public void sendMsg() {
		ConcurrentHashMap<String, ChannelGroup> concurrentHashMap = Global.concurrentHashMap;
		for (Map.Entry<String, ChannelGroup> map : concurrentHashMap.entrySet()) {
			String key = map.getKey();
			ChannelGroup channelGroup = map.getValue();
			TextWebSocketFrame contws = new TextWebSocketFrame( "zale服务端返回：" + (int) new Random().nextInt( 100 ) );
			channelGroup.writeAndFlush( contws );
		}
		// 群发组
		// Global.group.writeAndFlush( contws );
	}

	@RabbitListener(queues = { IQueueConstants.QUEUE__LINE_PRICE })
	public void processMessage(Channel channel, Message message) {
		System.out.println( "MessageConsumer收到消息：" + new String( message.getBody() ) );
		try {
			channel.basicAck( message.getMessageProperties().getDeliveryTag(), false );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

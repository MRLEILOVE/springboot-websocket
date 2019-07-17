package com.bittrade.netty.handler;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

@Component
public class TestJob {

	@Scheduled(cron = "0/10 * * * * ?")
	public void sendMsg() {
		ConcurrentHashMap<String, ChannelGroup> concurrentHashMap = Global.concurrentHashMap;
		for (Map.Entry<String, ChannelGroup> map : concurrentHashMap.entrySet()) {
			String key = map.getKey();
			System.out.println( "**********************************************" + key );
			ChannelGroup channelGroup = map.getValue();
			TextWebSocketFrame contws = new TextWebSocketFrame( "zale服务端返回：" + (int) new Random().nextInt( 100 ) );
			channelGroup.writeAndFlush( contws );
		}
		// 群发组
		// Global.group.writeAndFlush( contws );
	}

}

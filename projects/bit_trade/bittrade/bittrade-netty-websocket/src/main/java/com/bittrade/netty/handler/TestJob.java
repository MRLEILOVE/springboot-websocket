package com.bittrade.netty.handler;

import java.util.Random;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

@Component
public class TestJob {

	@Scheduled(cron = "0/10 * * * * ?")
	public void sendMsg() {
		TextWebSocketFrame contws = new TextWebSocketFrame( "zale服务端返回：" + (int) new Random().nextInt( 100 ) );
		// 群发组
		Global.group.writeAndFlush( contws );
	}

}

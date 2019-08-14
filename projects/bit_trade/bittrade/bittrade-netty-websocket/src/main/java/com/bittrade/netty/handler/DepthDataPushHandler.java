package com.bittrade.netty.handler;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.bittrade.common.constant.IQueueConstants;
import com.bittrade.netty.enums.WebSocketEnum.DepthEnum;
import com.rabbitmq.client.Channel;

import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

@Component
public class DepthDataPushHandler {

	@RabbitListener(queues = IQueueConstants.QUEUE__ENTRUST)
	public void processMessage60(Channel channel, Message message) {
		String context = new String( message.getBody() );
		System.out.println( context );
		JSONObject jsonObject = JSONObject.parseObject( context );
		String symbol = jsonObject.getString( "symbol" );
		String key = symbol + "_" + DepthEnum.SYMBOL_DEPTH.getKey();
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

	/*
	 * public static void main(String[] args) { try { String str =
	 * "{\"symbol\":\"ETH-USDT\",\"sell\":[{\"price\":194.90,\"count\":36.127251},{\"price\":195.43,\"count\":35.328365}],\"buy\":[]}";
	 * JSONObject jsonObject = JSONObject.parseObject( str ); String symbol =
	 * jsonObject.getString( "symbol" ); String sell = jsonObject.getString(
	 * "sell" ); String buy = jsonObject.getString( "buy" ); JSONArray array =
	 * JSONArray.parseArray( sell ); System.out.println( sell ); } catch
	 * (Exception e) { e.printStackTrace(); } }
	 */

}

package com.bittrade.netty.handler;

import java.util.ArrayList;
import java.util.List;

import com.bittrade.netty.dto.TickerDto;
import com.bittrade.netty.service.ITickerService;
import com.bittrade.netty.service.impl.TickerServiceImpl;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * 管理系统中的所有的pack provider
 * 
 * @描述
 * @作者 huan
 * @时间 2017年12月26日 - 下午9:04:41
 */
public class TickerManager {

	private static List<ITickerService> lists;

	static {
		lists = new ArrayList<>( 1 );
		lists.add( new TickerServiceImpl() );
	}

	public static void invoked(TextWebSocketFrame tws, ChannelHandlerContext context, TickerDto tickerDto) {
		for (ITickerService list : lists) {
			String res = list.getTickerBySymbol( context, tickerDto );
			TextWebSocketFrame contws = new TextWebSocketFrame( "服务端返回：" + res );
			// 群发组
			context.channel().writeAndFlush( contws );

			// 单独给Channel发送
			// Global.group.writeAndFlush( contws );
		}
	}

}

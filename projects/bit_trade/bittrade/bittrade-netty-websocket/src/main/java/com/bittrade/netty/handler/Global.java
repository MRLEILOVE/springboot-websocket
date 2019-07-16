package com.bittrade.netty.handler;

import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.group.ChannelGroup;

public class Global {

	public static ConcurrentHashMap<String, ChannelGroup> concurrentHashMap = new ConcurrentHashMap<String, ChannelGroup>();

	// 所有用户組
	// public static ChannelGroup group = new DefaultChannelGroup(
	// GlobalEventExecutor.INSTANCE );

	static {
		//concurrentHashMap.put( KlineEnum.BTC_USDT_1M.getKey(), new DefaultChannelGroup( GlobalEventExecutor.INSTANCE ) );
	}

}

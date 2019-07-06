package com.bittrade.netty.handler;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class Global {
	
	// 所有用户組
	public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

}

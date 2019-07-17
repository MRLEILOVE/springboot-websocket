package com.bittrade.netty.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;

public class Global {

	public static ConcurrentHashMap<String, List<String>>	channelGroups		= new ConcurrentHashMap<String, List<String>>();
	public static ConcurrentHashMap<String, ChannelGroup>					concurrentHashMap	= new ConcurrentHashMap<String, ChannelGroup>();

	// 所有用户組
	// public static ChannelGroup group = new
	// DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	public static List<String>												granularitys		= new ArrayList<String>();

	static {
		granularitys.add( "60" );
		granularitys.add( "180" );
		granularitys.add( "300" );
		granularitys.add( "900" );
		granularitys.add( "1800" );
		granularitys.add( "3600" );
		granularitys.add( "7200" );
		granularitys.add( "14400" );
		granularitys.add( "21600" );
		granularitys.add( "43200" );
		granularitys.add( "86400" );
	}

}

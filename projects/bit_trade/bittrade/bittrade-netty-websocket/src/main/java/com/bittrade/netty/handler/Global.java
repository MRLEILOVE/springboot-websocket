package com.bittrade.netty.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.bittrade.common.enums.KLineGranularityEnumer;

import io.netty.channel.group.ChannelGroup;

public class Global {

	public static ConcurrentHashMap<String, List<String>>	channelGroups		= new ConcurrentHashMap<String, List<String>>();
	public static ConcurrentHashMap<String, ChannelGroup>	concurrentHashMap	= new ConcurrentHashMap<String, ChannelGroup>();
	public static List<String>								granularitys		= new ArrayList<String>();

	static {
		for (KLineGranularityEnumer str : KLineGranularityEnumer.values()) {
			granularitys.add( str.getCode().toString() );
		}

	}

}

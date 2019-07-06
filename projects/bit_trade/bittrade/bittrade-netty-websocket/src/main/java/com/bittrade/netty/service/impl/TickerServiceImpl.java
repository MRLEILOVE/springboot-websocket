package com.bittrade.netty.service.impl;

import com.bittrade.netty.dto.TickerDto;
import com.bittrade.netty.service.ITickerService;

import io.netty.channel.ChannelHandlerContext;

public class TickerServiceImpl implements ITickerService {

	@Override
	public String getTickerBySymbol(ChannelHandlerContext context, TickerDto tickerDto) {
		return String.valueOf( (int) (1 + Math.random() * 10) );
	}

}

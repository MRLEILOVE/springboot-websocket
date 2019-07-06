package com.bittrade.netty.service;

import com.bittrade.netty.dto.TickerDto;

import io.netty.channel.ChannelHandlerContext;

public interface ITickerService {

	public String getTickerBySymbol(ChannelHandlerContext context, TickerDto tickerDto);

}

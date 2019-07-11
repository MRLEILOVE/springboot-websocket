package com.bittrade.common.constant;

public interface IQueueConstants {

	// 消息交换
	String MESSAGE_EXCHANGE = "message.direct.myexchange";
	// 消息队列名称
	String MESSAGE_QUEUE_NAME = "message.myqueue";
	// 消息路由键
	String MESSAGE_ROUTE_KEY = "message.myroute";

	// 私信消息交换
	String MESSAGE_EXCHANGE_DL = "message.direct.dlexchange";
	// 私信消息队列名称
	String MESSAGE_QUEUE_NAME_DL = "message.dlqueue";
	// 私信消息路由键
	String MESSAGE_ROUTE_KEY_DL = "message.dlroute";

}

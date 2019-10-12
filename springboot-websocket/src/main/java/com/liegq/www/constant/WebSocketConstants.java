package com.liegq.www.constant;


/**
 * WebSocket 相关常量
 */
public interface WebSocketConstants {

	/**
	 * 一推一发送消息目的地
	 */
	String ONE_TO_ONE_MESSAGE_RESPONSE_DESTINATION = "/messages/one";

	/**
	 * 一推多发送消息目的地
	 */
	String ONE_TO_MANY_MESSAGE_RESPONSE_DESTINATION = "/messages/many";

}

package com.liegq.www.constant;


/**
 * WebSocket 相关常量
 */
public interface WebSocketConstants {

	/**
	 * 一推一发送消息目的地
	 */
	String ONE_TO_ONE_MESSAGE_RESPONSE_DESTINATION = "/app/user/%s/queue/messages";

	/**
	 * 广播发送消息目的地
	 */
	String TOPIC_MESSAGE_RESPONSE_DESTINATION = "/app/topic/messages";

}

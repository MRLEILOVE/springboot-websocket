package com.liegq.www.task;

import com.liegq.www.bean.ServerMessage;
import com.liegq.www.constant.WebSocketConstants;
import com.liegq.www.util.WsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WebSocketTask {

	@Autowired
	private WsUtil wsUtil;

	/**
	 * 后台主动推送广播消息
	 */
	@Scheduled(fixedRate = 1000)
	public void sendTopicMessage() {
		log.warn("后台主动推送广播消息.....");
		ServerMessage serverMessage = ServerMessage.builder()
				.responseMessage("这个是后台主动推送的广播消息")
				.build();
		wsUtil.pushMsg(null, WebSocketConstants.TOPIC_MESSAGE_RESPONSE_DESTINATION, serverMessage);
	}

	/**
	 * 后台主动推送一对一消息
	 */
	@Scheduled(fixedRate = 1000)
	public void sendQueueMessage() {
		log.warn("后台主动推送一对一消息.....");
		ServerMessage serverMessage = ServerMessage.builder()
				.responseMessage("这个是后台主动推送的一对一消息")
				.build();
		wsUtil.pushMsg(null, String.format(WebSocketConstants.ONE_TO_ONE_MESSAGE_RESPONSE_DESTINATION, "1"), serverMessage);
	}


}

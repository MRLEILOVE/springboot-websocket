package com.liegq.www.controller;

import com.liegq.www.bean.ClientMessage;
import com.liegq.www.bean.ServerMessage;
import com.liegq.www.constant.WebSocketConstants;
import com.liegq.www.util.WsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class WebSocketController {

	@Autowired
	private WsUtil wsUtil;


	/**
	 * TODO 百度到底咋用？？
	 * 接收客户端主动发送的消息<br/>
	 * <br>创建人： leigq
	 * <br>创建时间： 2018-11-19 16:44
	 * <br>
	 *
	 * @param message 客户端发送消息实体
	 * @return
	 */
	@MessageMapping("/send/many")
	public void receiveMsg(@RequestBody ClientMessage message) {
		log.warn("receiveMsg 接收到了信息 [{}]", message);
		wsUtil.pushMsg(null, "/topic" + WebSocketConstants.TOPIC_MESSAGE_RESPONSE_DESTINATION, message);
	}


	/**
	 * 订阅广播主题
	 * <br/>
	 * <br>创建人： leigq
	 * <br>创建时间： 2018-11-19 16:56
	 * <br>
	 */
	@SubscribeMapping("/topic/messages")
	public ServerMessage subscribeTopic() {
		log.warn("XXX用户订阅了我。。。");
		return new ServerMessage("感谢你订阅了我。。。");
	}


	/**
	 * 订阅一对一主题
	 * <br/>
	 * <br>创建人： leigq
	 * <br>创建时间： 2018-11-19 16:56
	 * <br>
	 */
	@SubscribeMapping("/user/1/queue/messages")
	public ServerMessage subscribeOne() {
//		log.warn("用户 [{}] 订阅了我。。。", user);
//		return new ServerMessage(String.format("感谢用户 %s 订阅了我。。。", user));
		log.warn("XXX用户订阅了我。。。");
		return new ServerMessage("感谢你订阅了我。。。");
	}


}

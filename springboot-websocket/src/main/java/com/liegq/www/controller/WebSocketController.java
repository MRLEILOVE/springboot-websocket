package com.liegq.www.controller;

import com.liegq.www.bean.ClientMessage;
import com.liegq.www.bean.ServerMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ws")
@Slf4j
public class WebSocketController {

	/**
	 * 发送一对一消息<br/>
	 * <br>创建人： leigq
	 * <br>创建时间： 2018-11-19 16:44
	 * <br>
	 *
	 * @param message 客户端发送消息实体
	 * @return
	 */
	@PostMapping("/send/one")
	public ServerMessage sendOne(ClientMessage message) {
		log.warn("sendOne 接收到了信息 [{}]", message);
		return new ServerMessage(String.format("你发送的消息为: %s", message.getMessage()));
	}


	/**
	 * 发送一对多消息<br/>
	 * <br>创建人： leigq
	 * <br>创建时间： 2018-11-19 16:44
	 * <br>
	 *
	 * @param message 客户端发送消息实体
	 * @return
	 */
	@MessageMapping("/send/many")
	/*
	 * 这里加了@SendTo("/topic/subscribe"),则客户端请求/send/many时,会将请求发送至/topic/subscribeTest
	 * 客户端需要订阅了这个主题(/topic/subscribeTest)才能收到返回消息.
	 * */
//	@SendTo("/topic/subscribe")
	public ServerMessage sendMany(ClientMessage message) {
		log.warn("sendMany 接收到了信息 [{}]", message);
		return new ServerMessage(String.format("sendMany你发送的消息为: %s", message.getMessage()));
	}


	/**
	 * 订阅主题
	 * <br/>
	 * 一推一，一推多都可以订阅，区别在于前缀不同：一推一前缀：/user/{标识}/，一推多前缀：/topic/，
	 * 前缀是在 WebSocketConfig -> configureMessageBroker() 中配置
	 * <br>创建人： leigq
	 * <br>创建时间： 2018-11-19 16:56
	 * <br>
	 */
	@SubscribeMapping("/subscribe")
	public ServerMessage subscribe() {
		log.warn("XXX用户订阅了我。。。");
		return new ServerMessage("感谢你订阅了我。。。");
	}


}

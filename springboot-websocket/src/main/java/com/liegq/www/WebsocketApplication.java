package com.liegq.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@SpringBootApplication
/*
 * 启用Web Socket Message Broker
 * @EnableWebSocketMessageBroker注解用于开启使用STOMP协议来传输基于代理（MessageBroker）的消息，
 *这时候控制器（controller）开始支持@MessageMapping,就像是使用@requestMapping一样
 * */
@EnableWebSocketMessageBroker
public class WebsocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsocketApplication.class, args);
	}

}

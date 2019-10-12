package com.liegq.www.config.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * springboot websocket stomp配置
 * <br/>
 * 参考:<a href='https://blog.csdn.net/qq_28988969/article/details/78113463'>SpringBoot中建立WebSocket连接(STOMP)</a>
 * <p>
 * 创建人：leigq <br>
 * 创建时间：2018-11-19 10:24 <br>
 * <p>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注： <br>
 * </p>
 */
@Configuration
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Autowired
	private MyHandShakeInterceptor myHandShakeInterceptor;

	@Autowired
	private MyChannelInterceptor myChannelInterceptor;

	/**
	 * 注册stomp的端点
	 * <br>创建人： leigq
	 * <br>创建时间： 2018-11-19 10:29
	 * <br>
	 *
	 * @param registry stomp端点注册对象
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		/*
		 * 允许使用socketJs方式访问，访问点为ws，允许跨域
		 * 在网页上我们就可以通过这个链接
		 * http://localhost:8080/ws
		 * 来和服务器的WebSocket连接
		 * */
		registry.addEndpoint("/ws")
				.setAllowedOrigins("*")
				// 添加自定义拦截
				.addInterceptors(myHandShakeInterceptor)
				.withSockJS();
	}

	/**
	 * 配置信息代理
	 * <br>创建人： leigq
	 * <br>创建时间： 2018-11-19 10:30
	 * <br>
	 *
	 * @param registry 信息代理注册对象
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		/*
		 * 启用代理
		 * /user:代表点对点，即发指定用户
		 * /topic:代表发布广播，即群发
		 * */
		registry.enableSimpleBroker("/user", "/topic");
		/*
		 * 全局使用的消息前缀（客户端订阅路径上会体现出来）
		 * 例如客户端发送消息的目的地为/app/sendTest，则对应控制层@MessageMapping(“/sendTest”)
		 * 客户端订阅主题的目的地为/app/subscribeTest，则对应控制层@SubscribeMapping(“/subscribeTest”)
		 * */
		registry.setApplicationDestinationPrefixes("/app");
		// 点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
		registry.setUserDestinationPrefix("/user/");
	}

	/**
	 * 配置客户端入站通道
	 * <br>创建人： leigq
	 * <br>创建时间： 2018-11-19 14:45
	 * <br>
	 *
	 * @param registration 通道注册对象
	 */
	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		registration.interceptors(myChannelInterceptor);
	}
}

package com.liegq.www.config.ws;

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
		 * 将"/ws"路径注册为STOMP端点，这个路径与发送和接收消息的目的路径有所不同，
		 * 这是一个端点，客户端在订阅或发布消息到目的地址前，要连接该端点，
		 * 即客户端要先发送请求 url="ws://host:port/serverName/ws" 与 STOMP server 进行连接，之后才能用其他 url 订阅、发送、接收消息
		 */
		registry.addEndpoint("/ws")
				// 允许跨域
				.setAllowedOrigins("*")
				// 添加自定义拦截
				.addInterceptors(myHandShakeInterceptor)
				// 允许使用socketJs方式访问
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
		// （#1 内存代理设置）表示在topic、user、app这3个域上服务端可以向客户端发消息
		registry.enableSimpleBroker("/topic", "/user", "/app");

		// （#2 专业代理设置，1和2二选一）
//      registry.enableStompBrokerRelay("/topic", "/user")
//      .setRelayHost("127.0.0.1") // 必须要用ip，不能用 localhost 或者 tcp://localhost，否则会报【Message broker not active】错误！
//      .setRelayPort(61613); // 端口号。用户名和密码是可选的，默认都是guest

		// 表示客户端向服务器端发送的加上"/app"或"/user"前缀的命令才会进入@MessageMapping或@SubscribeMapping注解处理 (看源码注释)
		registry.setApplicationDestinationPrefixes("/app", "/user");
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

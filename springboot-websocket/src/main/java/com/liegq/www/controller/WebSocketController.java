package com.liegq.www.controller;

import com.liegq.www.bean.ClientMessage;
import com.liegq.www.bean.ServerMessage;
import com.liegq.www.constant.WebSocketConstants;
import com.liegq.www.util.WsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * websocket控制层
 * <h3>详细说明:</h3>
 * <b>@MessageMapping(“/sendTest”)</b><br/>
 * 接收客户端发送的消息，当客户端发送消息的目的地为<b>/app/sendTest</b> 时，交给该注解所在的方法处理消息，
 * 其中<b>/app</b>是在<b>registry.setApplicationDestinationPrefixes("/app");</b>一步配置的客户端请求前缀.
 * <br/>
 * 若没有添加<b>@SendTo</b>注解且该方法有返回值，则返回的目的地地址为<b>/topic/sendTest</b>，<b>经过消息代理</b>，
 * 客户端需要订阅了这个主题才能收到返回消息.
 * <hr/>
 * <b>@SubscribeMapping(“/subscribeTest”) </b>
 * 接收客户端发送的订阅，当客户端订阅的目的地为<b>/app/subscribeTest</b>时，交给该注解所在的方法处理订阅，其中<b>/app</b>为客户端请求前缀
 * 若没有添加<b>@SendTo</b>注解且该方法有返回值，则返回的目的地地址为<b>/app/sendTest</b>，<b>不经过消息代理</b>，客户端需要订阅了这个主题才能收到返回消息
 * <hr/>
 * <b>@SendTo(“/topic/subscribeTest”) </b>
 * 修改返回消息的目的地地址为<b>/topic/subscribeTest</b>，<b>经过消息代理</b>，客户端需要订阅了这个主题才能收到返回消息
 * <br/>
 * 参考:
 * <ul>
 * <li>
 * <a href='https://blog.csdn.net/qq_28988969/article/details/78113463'>SpringBoot中建立WebSocket连接(STOMP)</a>
 * </li>
 * <li>
 * <a href='https://www.cnblogs.com/Java-dzz/p/6179317.html'>在spring boot中使用webSocket组件（一）</a>
 * </li>
 * </ul>
 * <p>
 * 创建人：leigq <br>
 * 创建时间：2018-11-19 10:41 <br>
 * <p>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注： <br>
 * </p>
 */
@RestController
@Slf4j
public class WebSocketController {

	/**
	 * 接收客户端主动发送的消息<br/>
	 * <br>创建人： leigq
	 * <br>创建时间： 2018-11-19 16:44
	 * <br>
	 *
	 * @param message 客户端发送消息实体
	 * @return
	 */
	@MessageMapping("/messages/action/send")
	// SendTo 发送至 Broker 下的指定订阅路径, 作用跟convertAndSend类似，广播发给与该通道相连的客户端，类似的注解还有 @SendToUser
	@SendTo("/app/topic/messages")
	public ServerMessage receiveMsg(@RequestBody ClientMessage message) {
		log.warn("receiveMsg 接收到了信息 [{}]", message);
		return ServerMessage.builder()
				.responseMessage(message.toString())
				.build();
	}


	/**
	 * 订阅广播主题
	 * <br/>
	 * 客户端订阅时触发的方法：当客户端订阅的目的地为 /app/topic/kline 时，交给该注解所在的方法处理订阅，其中 /app 为客户端请求前缀。
	 * 若没有添加 @SendTo 注解且该方法有返回值，则返回的目的地地址为 /app/topic/kline ，消息不经过消息代理并原路返回（客户端需要订阅了这个主题才能收到返回消息），
	 * 即使其他客户端也订阅了这个目的地，也不会受到消息。所以，这个注解很适合做界面订阅数据时的初始化。
	 * 参考：https://blog.csdn.net/qq_28988969/article/details/78113463
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
	@SubscribeMapping("/user/{user}/queue/messages")
	public ServerMessage subscribeOne(@DestinationVariable String user) {
		log.warn("用户 [{}] 订阅了我。。。", user);
		return new ServerMessage(String.format("感谢用户 %s 订阅了我。。。", user));
	}


}

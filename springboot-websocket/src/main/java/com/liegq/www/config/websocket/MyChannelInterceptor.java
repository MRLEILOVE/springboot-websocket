//package com.liegq.www.config.websocket;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.messaging.simp.stomp.StompCommand;
//import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
//import org.springframework.messaging.support.ChannelInterceptor;
//import org.springframework.stereotype.Component;
//
/**
 * TODO 百度这个到底咋用？？？
 * 通道拦截器<br/>
 * 可以在 Message 对象在发送到 MessageChannel 前后查看修改此值，
 * 也可以在 MessageChannel 接收 MessageChannel 对象前后修改此值
 * <p>
 * 创建人：leigq <br>
 * 创建时间：2018-11-19 14:46 <br>
 * <p>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注： <br>
 * </p>
// */
//@Component
//@Slf4j
//public class MyChannelInterceptor implements ChannelInterceptor {
//
//	@Autowired
//	private SimpMessagingTemplate simpMessagingTemplate;
//
//	/**
//	 * 接收 MessageChannel 对象前
//	 *
//	 * @param channel {@link MessageChannel}
//	 * @return
//	 */
//	@Override
//	public boolean preReceive(MessageChannel channel) {
//		log.warn("[{}] preReceive。。。。", this.getClass().getCanonicalName());
//		return true;
//	}
//
//	/**
//	 * 发送到 MessageChannel 前
//	 *
//	 * @param message {@link Message}
//	 * @param channel {@link MessageChannel}
//	 * @return
//	 */
//	@Override
//	public Message<?> preSend(Message<?> message, MessageChannel channel) {
//		log.warn("[{}] preSend....", this.getClass().getCanonicalName());
//
//		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
//		StompCommand command = accessor.getCommand();
//		// 检测用户订阅内容（防止用户订阅不合法频道）
//		if (StompCommand.SUBSCRIBE.equals(command)) {
//		    log.warn("[{}] 用户订阅目的地 ---> [{}]", this.getClass().getCanonicalName(), accessor.getDestination());
//			// 如果该用户订阅的频道不合法直接返回null前端用户就接受不到该频道信息
//			return message;
//		} else {
//			return message;
//		}
//	}
//
//	/**
//	 * 发送到 MessageChannel 后
//	 *
//	 * @param message {@link Message}
//	 * @param channel {@link MessageChannel}
//	 * @param sent
//	 * @param ex
//	 */
//	@Override
//	public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
//        log.warn("[{}] afterSendCompletion.....", this.getClass().getCanonicalName());
//
//		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
//		StompCommand command = accessor.getCommand();
//		if (StompCommand.SUBSCRIBE.equals(command)) {
//		    log.warn("[{}] 订阅消息发送成功.....", this.getClass().getCanonicalName());
////			this.simpMessagingTemplate.convertAndSend("/topic/getResponse", "消息发送成功");
//		}
//		// 如果用户断开连接
//		if (StompCommand.DISCONNECT.equals(command)) {
//		    log.warn("[{}] 用户断开连接成功.....", this.getClass().getCanonicalName());
////			simpMessagingTemplate.convertAndSend("/topic/getResponse", "{'msg':'用户断开连接成功'}");
//		}
//	}
//
//}

package com.bittrade.c2c.websocket.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

/**
 * @author xzc
 * @date 2019-08-19 15:56
 * @description
 */
@Slf4j
@Component
public class TextMessageInterceptor implements ChannelInterceptor {


    /**
     * 在消息实际发送到通道之前调用
     *
     * @param message
     * @param channel
     * @return
     */
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        log.info("在消息实际发送到通道之前调用");
        return message;
    }

    /**
     * 在发送调用之后立即调用
     *
     * @param message
     * @param channel
     * @param sent
     */
    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        log.info("在发送调用之后立即调用");
    }
}

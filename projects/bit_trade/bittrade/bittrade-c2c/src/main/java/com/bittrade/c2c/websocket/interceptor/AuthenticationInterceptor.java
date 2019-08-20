package com.bittrade.c2c.websocket.interceptor;

import com.bittrade.c2c.util.RequestUtils;
import com.core.web.common.entity.LoginUser;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;
import java.util.Objects;

/**
 * @author xzc
 * @date 2019-08-19 15:44
 * @description 握手拦截器
 */
@Component
public class AuthenticationInterceptor implements HandshakeInterceptor {

    /**
     * 握手之前执行：主要实现连接websocket 权限验证
     *
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @param webSocketHandler
     * @param map
     * @return 返回true 继续握手 ，返回false 停止握手
     * @throws Exception
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        LoginUser currentUser = RequestUtils.getCurrentUser();
        if (Objects.nonNull(currentUser)) {
            return true;
        }
        return false;
    }

    /**
     * 在握手完成后执行：
     *
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @param webSocketHandler
     * @param e
     */
    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        //todo
    }
}

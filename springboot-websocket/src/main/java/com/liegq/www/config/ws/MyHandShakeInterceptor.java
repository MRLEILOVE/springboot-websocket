package com.liegq.www.config.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

/**
 * websocket握手拦截器
 * <br/>
 * 拦截websocket的握手请求。在服务端和客户端在进行握手时会被执行
 * 我们可以通过请求信息，比如token、或者session判用户是否可以连接，这样就能够防范非法用户
 * <p>
 * 创建人：leigq <br>
 * 创建时间：2018-11-19 14:23 <br>
 * <p>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注： <br>
 * </p>
 */
@Component
@Slf4j
public class MyHandShakeInterceptor extends HttpSessionHandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
								   WebSocketHandler wsHandler, Map<String, Object> attributes) {

        log.warn("[{}] http协议转换websoket协议进行前, 握手前 [{}]", this.getClass().getCanonicalName(), request.getURI());
        // http协议转换websoket协议进行前，可以在这里通过session信息判断用户登录是否合法
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        //握手成功后,
        log.warn("[{}] 握手成功后...", this.getClass().getCanonicalName());
    }
}

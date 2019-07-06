package com.bittrade.batch.socket;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.java_websocket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.bittrade.api.service.ITParamConfigService;
import com.bittrade.batch.enumer.ParamConfigEnum.ParamKeyEnum;
import com.bittrade.batch.general.ObtainParamConfigInfo;

@Component
public class WebSockeClientHandler {

	private static final Logger		LOG					= LoggerFactory.getLogger( WebSockeClientHandler.class );

	@Autowired
	private ITParamConfigService	paramConfigService;

	private int						connectFailCount	= 1;

	@PostConstruct
	public void init() {
		try {
			LOG.info( "************WebSockeClientHandler客户端开始连接************" );
			String[] symbols = ObtainParamConfigInfo.obtainRate( paramConfigService, ParamKeyEnum.OKEX_SYMBOL_KLINE_HISTORY_DATA_KEY.getKey() )
					.getParamValue().split( "," );
			StringBuffer sb = new StringBuffer( "{\"op\": \"subscribe\", \"args\":" );
			List<String> symbolList = new ArrayList<String>();
			for (String symbol : symbols) {
				// 订阅
				symbolList.add( "spot/ticker:" + symbol );
			}
			sb.append( JSON.toJSON( symbolList.toArray() ) );
			sb.append( "}" );
			String okexWebsocketUrl = ObtainParamConfigInfo.obtainRate( paramConfigService, ParamKeyEnum.OKEX_WEB_SOCKET_URL_KEY.getKey() )
					.getParamValue();
			// okexWebsocketUrl = "ws://127.0.0.1:7397";
			MyWebSocketClient client = new MyWebSocketClient( okexWebsocketUrl );
			client.connect();
			while (!client.getReadyState().equals( WebSocket.READYSTATE.OPEN )) {
				if (connectFailCount == 10) {
					throw new Exception( "WebSockeClient重试次数超过10次，请排查原因..." );
				}
				LOG.info( "WebSockeClient第" + connectFailCount + "次连接中..." );
				connectFailCount++;
				Thread.sleep( 2000 );
			}
			LOG.info( "建立websocket连接" );
			client.send( sb.toString() );// 开始订阅
		} catch (Exception e) {
			LOG.error( e.getMessage(), e );

		}
	}

}

package com.bittrade.batch.socket;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.compress.compressors.deflate64.Deflate64CompressorInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bittrade.batch.enumer.ParamConfigEnum;
import com.bittrade.batch.general.GeneralMethod;
import com.bittrade.common.utils.RedisKeyUtil;
import com.bittrade.currency.api.service.ITParamConfigService;
import com.bittrade.pojo.dto.OkexTickerDto;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import redis.clients.jedis.JedisCluster;

@Component
public class WebSocketClient {

	private static final Logger		LOG		= LoggerFactory.getLogger( WebSocketClient.class );

	@Reference
	private ITParamConfigService	paramConfigService;

	@Autowired
	private JedisCluster			jedisCluster;

	private final static String		PING	= "ping";

	private final static String		PONG	= "pong";

	@PostConstruct
	public void init() {
		LOG.info( "==============================socket开始连接==============================" );
		connect();
	}

	/**
	 * 连接/重连
	 */
	private void connect() {
		try {
			StringBuffer requestString = new StringBuffer( "{\"op\": \"subscribe\", \"args\":" );
			// 获取交易对

			String[] symbols = GeneralMethod
					.qryParamConfigInfo( paramConfigService, ParamConfigEnum.ParamKeyEnum.OKEX_SYMBOL_KLINE_HISTORY_DATA_KEY.getKey() )
					.getParamValue().split( "," );

			// 拼接请求参数
			List<String> symbolList = new ArrayList<String>();
			Arrays.asList( symbols ).stream().forEach( symbol -> symbolList.add( "spot/ticker:" + symbol ) );
			requestString.append( JSON.toJSON( symbolList.toArray() ) );
			requestString.append( "}" );

			String okexWebsocketUrl = GeneralMethod
					.qryParamConfigInfo( paramConfigService, ParamConfigEnum.ParamKeyEnum.OKEX_WEB_SOCKET_URL_KEY.getKey() ).getParamValue();

			Request request = new Request.Builder().url( okexWebsocketUrl ).build();

			OkHttpClient client = new OkHttpClient();
			client.newWebSocket( request, new WebSocketListener() {

				@Override
				public void onOpen(WebSocket webSocket, Response response) {
					LOG.info( "WebSocket已经建立连接，开始订阅，订阅内容requestString=" + requestString );
					webSocket.send( requestString.toString() );
				}

				@Override
				public void onMessage(WebSocket webSocket, String text) {
					LOG.info( text );
				}

				@Override
				public void onMessage(WebSocket webSocket, ByteString bytes) {
					String key = jedisCluster.get( RedisKeyUtil.getOkexPingKey() );
					if (StringUtils.isEmpty( key )) {
						// 设置ping的过期时间为30秒
						LOG.info( "给服务端发送心跳包....." );
						jedisCluster.setex( RedisKeyUtil.getOkexPingKey(), 30, RedisKeyUtil.getOkexPingKey() );
						webSocket.send( PING );
					}

					String text = uncompress( bytes.toByteArray() );
					if(PONG.equals(text)){
						return;
					}
					LOG.info( "*******************text*******************" + text );
					JSONObject jsonObject = (JSONObject) JSON.parse( text );

					String table = jsonObject.getString( "table" );
					// 如果是公共-Ticker频道：获取现货最新成交价、买一价、卖一价和24交易量则进行操作
					if (table != null && table.equals( "spot/ticker" )) {
						JSONArray jSONArray = jsonObject.getJSONArray( "data" );
						jSONArray.forEach( data -> {
							OkexTickerDto okexTickerDto = (OkexTickerDto) JSONObject.parseObject( data.toString(), OkexTickerDto.class );
							jedisCluster.setex( RedisKeyUtil.getOkexSymbolLast( okexTickerDto.getInstrument_id() ), 300,
									String.valueOf( okexTickerDto.getLast() ) );
						} );
					}
				}

				@Override
				public void onClosing(WebSocket webSocket, int code, String reason) {
					LOG.error( "WebSocket.onClosing,reason=" + reason, "五秒后重新连接......" );
					try {
						Thread.sleep( 5000 );
						connect();
					} catch (Exception e) {
						e.getStackTrace();
					}
				}

				@Override
				public void onClosed(WebSocket webSocket, int code, String reason) {
					LOG.error( "WebSocket.onClosing,reason=" + reason, "五秒后重新连接......" );
					try {
						Thread.sleep( 5000 );
						connect();
					} catch (Exception e) {
						e.getStackTrace();
					}
				}

				@Override
				public void onFailure(WebSocket webSocket, Throwable t, Response response) {
					LOG.error( "WebSocket.onFailure,t=" + t, "五秒后重新连接......" );
					try {
						Thread.sleep( 5000 );
						connect();
					} catch (Exception e) {
						e.getStackTrace();
					}
				}
			} );
		} catch (Exception e) {
			LOG.error( "WebSocket.Exception,e=" + e.toString(), "五秒后重新连接......" );
			try {
				Thread.sleep( 5000 );
				connect();
			} catch (Exception e1) {
				e1.getStackTrace();
			}
		}
	}

	/**
	 * 解压
	 */
	private static String uncompress(byte[] bytes) {
		try (final ByteArrayOutputStream out = new ByteArrayOutputStream();
				final ByteArrayInputStream in = new ByteArrayInputStream( bytes );
				final Deflate64CompressorInputStream zin = new Deflate64CompressorInputStream( in )) {
			final byte[] buffer = new byte[1024];
			int offset;
			while (-1 != (offset = zin.read( buffer ))) {
				out.write( buffer, 0, offset );
			}
			return out.toString();
		} catch (final IOException e) {
			throw new RuntimeException( e );
		}
	}

}

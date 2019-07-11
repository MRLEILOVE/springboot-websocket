package com.bittrade.batch.socket;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;

import javax.annotation.PostConstruct;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bittrade.batch.enumer.ParamConfigEnum;
import com.bittrade.batch.general.GeneralMethod;
import com.bittrade.common.utils.RedisKeyUtil;
import com.bittrade.currency.api.service.ITParamConfigService;
import com.bittrade.pojo.dto.OkexTickerDto;
import com.bittrade.pojo.model.TParamConfig;
import org.apache.commons.compress.compressors.deflate64.Deflate64CompressorInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import redis.clients.jedis.JedisCluster;

@Component
public class MyWebSocketClient {

	private static final Logger LOG					= LoggerFactory.getLogger( MyWebSocketClient.class );

	@Reference
	private static ITParamConfigService paramConfigService;
	@Autowired
	private static JedisCluster jedisCluster;

	private static String PING_KEY = "ping";

	@PostConstruct
	public void init() {
		connect();
	}

	public static void main(String[] args) {
//		connect();

		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder().url( "wss://real.okex.com:10442/ws/v3" ).build();

		client.newWebSocket( request, new WebSocketListener() {
			@Override
			public void onOpen(WebSocket webSocket, Response response) {
//				webSocket.send( "{\"op\": \"subscribe\", \"args\": [\"spot/ticker:ETH-USDT\",\"spot/ticker:BTC-USDT\"]}" );
				webSocket.send("ping");
			}


			@Override
			public void onMessage(WebSocket webSocket, String text) {
				System.out.println("aaaaaaaaaaaa*******"+text);
			}

			@Override
			public void onMessage(WebSocket webSocket, ByteString bytes) {
				System.out.println( "bytes.toByteArray()=" + bytes.toByteArray() );
				System.out.println( "uncompress( bytes.toByteArray() )=" + uncompress( bytes.toByteArray() ) );
			}

			@Override
			public void onClosing(WebSocket webSocket, int code, String reason) {
				System.out.println( webSocket );
			}

			@Override
			public void onClosed(WebSocket webSocket, int code, String reason) {
				System.out.println( webSocket );
			}

			@Override
			public void onFailure(WebSocket webSocket, Throwable t, Response response) {
				System.out.println( t.getMessage() );
			}
		});

	}



	/**
	 * 连接/重连
	 */
	private void connect(){
		try {
			StringBuffer requestString = new StringBuffer( "{\"op\": \"subscribe\", \"args\":" );
			//获取交易对
//			String[] symbols = ObtainParamConfigInfo.obtainRate( paramConfigService, ParamConfigEnum.ParamKeyEnum.OKEX_SYMBOL_KLINE_HISTORY_DATA_KEY.getKey() )
//					.getParamValue().split( "," );

			String[] symbols = GeneralMethod.qryParamConfigInfo( paramConfigService, ParamConfigEnum.ParamKeyEnum.OKEX_SYMBOL_KLINE_HISTORY_DATA_KEY.getKey() )
					.getParamValue().split( "," );

			if(requestString == null){
				LOG.error("交易对为空，获取交易对成交价失败！！！");
				return;
			}

			//拼接请求参数
			List<String> symbolList = new ArrayList<String>();
			Arrays.asList(symbols).stream().forEach(symbol -> symbolList.add( "spot/ticker:" + symbol ));
			requestString.append( JSON.toJSON( symbolList.toArray() ) );
			requestString.append( "}" );


			LOG.info( "********** 交易对成交价查询 ***********************" );
//			LOG.info( "请求参数：" + requestString );
			OkHttpClient client = new OkHttpClient();

//			String webSocketUrl = GeneralMethod.qryParamConfigInfo( paramConfigService, ParamConfigEnum.ParamKeyEnum.OKEX_WEB_SOCKET_URL_KEY.getKey() ).getParamValue();
//			Request request = new Request.Builder().url( webSocketUrl ).build();
			Request request = new Request.Builder().url( "wss://real.okex.com:10442/ws/v3" ).build();

//			Request request = new Request.Builder().url("ws://127.0.0.1:7397").build();

			client.newWebSocket( request, new WebSocketListener() {

				@Override
				public void onOpen(WebSocket webSocket, Response response) {
					//握手
//					webSocket.send( "{\"op\": \"subscribe\", \"args\": [\"spot/ticker:ETH-USDT\",\"spot/ticker:BTC-USDT\"]}" );
					webSocket.send(requestString.toString());
				}


				@Override
				public void onMessage(WebSocket webSocket, String text) {
				}

				@Override
				public void onMessage(WebSocket webSocket, ByteString bytes) {

					String key = jedisCluster.get(PING_KEY);
					if(null == key){
						//设置ping的过期时间为30秒
						jedisCluster.setex(PING_KEY,30,"pong");
						webSocket.send("ping");
					}


					String text = uncompress(bytes.toByteArray());
					System.out.println( "解压后的数据" + text);

					JSONObject jsonObject = (JSONObject) JSON.parse( text );

					String table = jsonObject.getString("table");
					//如果是公共-Ticker频道：获取现货最新成交价、买一价、卖一价和24交易量则进行操作
					if(table != null && table.equals("spot/ticker")){
						JSONArray jSONArray = jsonObject.getJSONArray("data");
						jSONArray.forEach(data ->{
							OkexTickerDto okexTickerDto = (OkexTickerDto) JSONObject.parseObject(data.toString(), OkexTickerDto.class);
							System.out.println(okexTickerDto);
							jedisCluster.set( RedisKeyUtil.getOkexSymbolLast( okexTickerDto.getInstrument_id() ), String.valueOf( okexTickerDto.getLast() ) );
						});
					}

				}

				@Override
				public void onClosing(WebSocket webSocket, int code, String reason) {
					LOG.info("*****************WebSocket重新连接**********************");
					connect();
				}

				@Override
				public void onClosed(WebSocket webSocket, int code, String reason  ) {
					LOG.info("*****************WebSocket重新连接**********************");
					connect();
				}

				@Override
				public void onFailure(WebSocket webSocket, Throwable t, Response response) {
					LOG.info("*****************WebSocket重新连接**********************");
					connect();
				}
			} );
		} catch (Exception e) {
			System.out.println( e );
		}
	}


	/**
	 * 解压
	 */
	private static String uncompress(byte[] bytes) {
		try (final ByteArrayOutputStream out = new ByteArrayOutputStream();
			 final ByteArrayInputStream in = new ByteArrayInputStream(bytes);
			 final Deflate64CompressorInputStream zin = new Deflate64CompressorInputStream(in)) {
			final byte[] buffer = new byte[1024];
			int offset;
			while (-1 != (offset = zin.read(buffer))) {
				out.write(buffer, 0, offset);
			}
			return out.toString();
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}


	/**
	 * 心跳和测试连接是否被断开
	 */
	private void heartbeat(WebSocket webSocket){
		System.out.println("*********心跳**********");
		Timer timer = new Timer();
		long delay = 28 * 1000;			//延迟28秒
		long period = 28 * 1000;  //28秒执行一次

		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				//key不存在，说明30秒没有响应，连接已经过期
				if(jedisCluster.exists(PING_KEY)){
					timer.cancel();//为避免资源浪费，关掉这个定时器，重连时会开启新的定时器
					connect();//重连
				}else{
					webSocket.send("ping");
				}
			}
		};
		timer.scheduleAtFixedRate(task,delay,period);
	}

}

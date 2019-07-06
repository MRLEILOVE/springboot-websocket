package com.bittrade.batch.socket;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.zip.Inflater;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bittrade.common.utils.RedisKeyUtil;
import com.bittrade.pojo.dto.OkexTickerDto;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import redis.clients.jedis.JedisCluster;

public class MyWebSocketClient extends WebSocketClient {

	private static final Logger	LOG	= LoggerFactory.getLogger( MyWebSocketClient.class );

	@Autowired
	private JedisCluster		jedisCluster;

	public MyWebSocketClient(String url) throws URISyntaxException {
		super( new URI( url ) );
	}

	@Override
	public void onOpen(ServerHandshake handshakedata) {
		System.out.println( "建立握手成功..." );
		for (Iterator<String> it = handshakedata.iterateHttpFields(); it.hasNext();) {
			String key = it.next();
			System.out.println( key + ":" + handshakedata.getFieldValue( key ) );
		}
	}

	@Override
	public void onMessage(String message) {
		LOG.info( "message=" + message );
		message = decode( message );
		//message = "{\"table\":\"spot/ticker\",\"data\":[{\"instrument_id\":\"ETH-USDT\",\"last\":\"162.09\",\"best_bid\":\"162.1\",\"best_ask\":\"162.12\",\"open_24h\":\"165.35\",\"high_24h\":\"166.55\",\"low_24h\":\"155.69\",\"base_volume_24h\":\"620062.9352195\",\"quote_volume_24h\":\"100084770.34604578\",\"timestamp\":\"2019-04-16T10:44:06.090Z\"}]}";
		JSONObject jsonObject = (JSONObject) JSON.parse( message );
		JSONArray jsonArray = (JSONArray) jsonObject.get( "data" );
		OkexTickerDto okexTickerDto = JSONObject.parseObject( jsonArray.get( 0 ).toString(), OkexTickerDto.class );
		jedisCluster.set( RedisKeyUtil.getOkexSymbolLast( okexTickerDto.getInstrument_id() ), String.valueOf( okexTickerDto.getLast() ) );
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		System.out.println( "关闭..." );
	}

	@Override
	public void onError(Exception e) {
		System.out.println( "异常" + e );
	}

	private static String decode(Object msg) {
		BinaryWebSocketFrame frameBinary = (BinaryWebSocketFrame) msg;
		byte[] bytes = new byte[frameBinary.content().readableBytes()];
		frameBinary.content().readBytes( bytes );
		ByteBuf byteBuf = Unpooled.wrappedBuffer( bytes );
		String str = uncompress( byteBuf );
		return str;
	}

	private static String uncompress(ByteBuf buf) {
		try {
			byte[] temp = new byte[buf.readableBytes()];
			ByteBufInputStream bis = new ByteBufInputStream( buf );
			bis.read( temp );
			bis.close();
			Inflater decompresser = new Inflater( true );
			decompresser.setInput( temp, 0, temp.length );
			StringBuilder sb = new StringBuilder();
			byte[] result = new byte[1024];
			while (!decompresser.finished()) {
				int resultLength = decompresser.inflate( result );
				sb.append( new String( result, 0, resultLength, "UTF-8" ) );
			}
			decompresser.end();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}

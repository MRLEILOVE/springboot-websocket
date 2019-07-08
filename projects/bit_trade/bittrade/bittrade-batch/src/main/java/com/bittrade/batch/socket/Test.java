package com.bittrade.batch.socket;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.commons.compress.compressors.deflate64.Deflate64CompressorInputStream;
import org.springframework.stereotype.Component;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

@Component
public class Test {

	@PostConstruct
	public void init() {
		try {
			System.out.println( "*********************************" );
			OkHttpClient client = new OkHttpClient();

			Request request = new Request.Builder().url( "wss://real.okex.com:10441/websocket?compress=true" ).build();
			// Request request = new Request.Builder().url(
			// "ws://127.0.0.1:7397" ).build();

			client.newWebSocket( request, new WebSocketListener() {

				@Override
				public void onOpen(WebSocket webSocket, Response response) {
					webSocket.send( "{\"op\": \"subscribe\", \"args\": [\"spot/ticker:ETH-USDT\"]}" );
				}

				@Override
				public void onMessage(WebSocket webSocket, String text) {
					System.out.println( text );
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
					System.out.println( webSocket );
				}
			} );
		} catch (Exception e) {
			System.out.println( e );
		}
	}

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

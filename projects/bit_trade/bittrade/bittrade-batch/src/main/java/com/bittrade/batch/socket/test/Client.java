package com.bittrade.batch.socket.test;

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class Client {

	public static void main(String[] args) throws URISyntaxException, InterruptedException {
		URI uri = new URI("ws://192.168.1.11:7397");
		WebSocketClient client = new WebSocketClient(uri) {
			@Override
			public void onOpen(ServerHandshake handshakedata) {
				System.out.println( "onOpen() handshakedata=" + handshakedata );
			}
			
			@Override
			public void onMessage(String msg) {
				System.out.println( "onMessage() msg=" + msg );
			}
			
			@Override
			public void onError(Exception ex) {
				System.out.println( "onError() ex=" + ex );
			}
			
			@Override
			public void onClose(int code, String reason, boolean remote) {
				System.out.println( "onClose() code=" + code + ", reason=" + reason );
			}
		};
		client.connect();
		
		Thread.sleep( 500 );
		client.send( "text-1" );
		client.sendPing();
		byte[] bArr = new byte[] {
			-119, -128, -4, 107, 73, 19
		};
		client.send( bArr );
		
		client.send( "text-2" );
		synchronized (Client.class) {
			Client.class.wait();
		}
		
		client.close();
	}
	
}

package com.bittrade.batch.socket.test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

//import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class Client {

	public static void test() throws URISyntaxException, InterruptedException {
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
//		client.send( "text-1" );
//		client.sendPing();
		byte[] bArr = new byte[] {
			-119, -128, -4, 107, 73, 19
//			-119, -128, -31, 18, 71, 0
//			-119, -128, 98, -12, -123, 123
		};
		client.send( bArr );
		
//		client.send( "text-2" );
		synchronized (Client.class) {
			Client.class.wait();
		}
		
		client.close();
	}
	
	private static void test2() throws UnknownHostException, IOException, InterruptedException {
		Socket socket = new Socket("192.168.1.11", 7397);
		OutputStream os = socket.getOutputStream();
		
		byte bArr[] = new byte[] {
				71, 69, 84, 32, 47, 32, 72, 84, 84, 80, 47, 49, 46, 49, 13, 10, 67, 111, 110, 110, 101, 99, 116, 105, 111, 110, 58, 32, 85, 112, 103, 114, 97, 100, 101, 13, 10, 72, 111, 115, 116, 58, 32, 49, 57, 50, 46, 49, 54, 56, 46, 49, 46, 49, 49, 58, 55, 51, 57, 55, 13, 10, 83, 101, 99, 45, 87, 101, 98, 83, 111, 99, 107, 101, 116, 45, 75, 101, 121, 58, 32, 43, 119, 119, 81, 99, 52, 82, 57, 100, 84, 69, 76, 76, 72, 74, 48, 70, 81, 79, 70, 115, 119, 61, 61, 13, 10, 83, 101, 99, 45, 87, 101, 98, 83, 111, 99, 107, 101, 116, 45, 86, 101, 114, 115, 105, 111, 110, 58, 32, 49, 51, 13, 10, 85, 112, 103, 114, 97, 100, 101, 58, 32, 119, 101, 98, 115, 111, 99, 107, 101, 116, 13, 10, 13, 10
		};
		int len = bArr.length;
		os.write( bArr, 0, len );
		
//		Thread.sleep( 1000 );
		
		int i = 6;
		while (i-- > 0) {
			byte bArr_2[] = new byte[] {
//					-119, -128, -4, 107, 73, 19
//					-119, -122, -98, -23, 18, -51, //23, 105, -18, -90, -41, -6
					-119, -122, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
			};
			int len_2 = bArr_2.length;
			System.out.println( "len_2=" + len_2 );
			os.write( bArr_2, 0, len_2 );
			
//			Thread.sleep( 1000 );
		}
		
		Thread.sleep(500);
		
		os.close();
		socket.close();
	}
	
	public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
//		test();
		test2();
		byte one = ( byte ) ( true ? -128 : 0 );
		one |= 9;
		System.out.println( one );
	}
	
}

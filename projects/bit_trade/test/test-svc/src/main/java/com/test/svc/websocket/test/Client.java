package com.test.svc.websocket.test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

//import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class Client {

	private static final byte bArr_PROTOCOL[] = new byte[] {
			71, 69, 84, 32, 47, 32, 72, 84, 84, 80, 47, 49, 46, 49, 13, 10, 67, 111, 110, 110, 101, 99, 116, 105, 111, 110, 58, 32, 85, 112, 103, 114, 97, 100, 101, 13, 10, 72, 111, 115, 116, 58, 32, 49, 57, 50, 46, 49, 54, 56, 46, 49, 46, 49, 49, 58, 55, 51, 57, 55, 13, 10, 83, 101, 99, 45, 87, 101, 98, 83, 111, 99, 107, 101, 116, 45, 75, 101, 121, 58, 32, 43, 119, 119, 81, 99, 52, 82, 57, 100, 84, 69, 76, 76, 72, 74, 48, 70, 81, 79, 70, 115, 119, 61, 61, 13, 10, 83, 101, 99, 45, 87, 101, 98, 83, 111, 99, 107, 101, 116, 45, 86, 101, 114, 115, 105, 111, 110, 58, 32, 49, 51, 13, 10, 85, 112, 103, 114, 97, 100, 101, 58, 32, 119, 101, 98, 115, 111, 99, 107, 101, 116, 13, 10, 13, 10
	};
	private static final byte bArr_CLOSE[] = new byte[] {
			-120, -128, 0, 0, 0, 0
	};
	private static final byte bArr_PING[] = new byte[] {
			-119, -128, -4, 107, 73, 19
//			-119, -122, 107, 78, 124, -10, -30, -50, -128, -99, 34, 93
//			-119, -122, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
//			-119, -128, 0, 0, 0, 0
	};
	public static final byte BARR_PING[] = new byte[] {
			-119, -122, 107, 78, 124, -10, -30, -50, -128, -99, 34, 93
	};
	private static final byte bArr_PONG[] = new byte[] {
			-118, -128, -89, 23, -81, 25
//			-118, -122, -59, 125, -121, 71, 79, -3, 32, 80, 106, 100
//			-118, -122, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
//			-118, -128, 0, 0, 0, 0
	};
	public static final byte BARR_PONG[] = new byte[] {
			-118, -122, -59, 125, -121, 71, 79, -3, 32, 80, 106, 100
	};

	public static void test() throws URISyntaxException, InterruptedException, IOException {
		URI uri = new URI("ws://192.168.1.12:7397"); // 11 12
		WebSocketClient client = new WebSocketClient(uri) {
			@Override
			public void onOpen(ServerHandshake handshakedata) {
				System.out.println( "onOpen() handshakedata=" + handshakedata );
				synchronized (Client.class) {
					Client.class.notify();
				}
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
		
		System.out.println( "11" );
		synchronized (Client.class) {
			Client.class.wait();
		}
		System.out.println( "22" );
		
//		client.send( "text-1" );
//		client.sendPing();
//		client.sendPong();
		StringBuilder strBud = new StringBuilder();
		for (int i = 0; i < bArr_PING.length; strBud.append( bArr_PING[i++] + ", " ));
		System.out.println( "bArr_PING.length=" + bArr_PING.length + ", strBud=" + strBud );
		client.send( bArr_PING );
		Thread.sleep( 1000 );
		strBud = new StringBuilder();
		for (int i = 0; i < bArr_PONG.length; strBud.append( bArr_PONG[i++] + ", " ));
		System.out.println( "bArr_PONG.length=" + bArr_PONG.length + ", strBud=" + strBud );
		client.send( bArr_PONG );
		
//		client.send( "text-2" );
		
		System.out.println( "System.in.read()=" + System.in.read() );
		
		client.close();
	}
	
	private static void test2() throws UnknownHostException, IOException, InterruptedException {
		Socket socket = new Socket("192.168.1.12", 7397); // 11 12
		OutputStream os = socket.getOutputStream();
		
		int len_protocol = bArr_PROTOCOL.length;
		System.out.println( "len_protocol=" + len_protocol );
		os.write( bArr_PROTOCOL, 0, len_protocol );
		os.flush();
		
//		Thread.sleep( 1000 );
		
		int i = 1; // 16
		while (i-- > 0) {
			byte[] bArr;
			
			bArr = BARR_PING; // bArr_PING
			int len_ping = bArr.length;
			System.out.println( "len_ping=" + len_ping );
			os.write( bArr, 0, len_ping );
			os.flush();
			
			Thread.sleep( 500 );
			
			bArr = BARR_PONG; // bArr_PONG
			int len_pong = bArr.length;
			System.out.println( "len_pong=" + len_pong );
			os.write( bArr, 0, len_pong );
			os.flush();
			
			Thread.sleep( 2000 );
		}

//		int len_close = bArr_CLOSE.length;
//		System.out.println( "len_close=" + len_close );
//		os.write( bArr_CLOSE, 0, len_close );
		
//		Thread.sleep(500);
		System.out.println( "System.in.read()=" + System.in.read() );
		
		os.close();
		socket.close();
	}
	
	public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
		test();
//		test2();
		
//		System.out.println( new String(bArr_PROTOCOL, "UTF-8") );
//		System.out.println( "-------------" );
//		System.out.println( new String(bArr_CLOSE, "UTF-8") );
//		System.out.println( new String(bArr_PING, "UTF-8") );
//		System.out.println( new String(bArr_PONG, "UTF-8") );
		
	}
	
}

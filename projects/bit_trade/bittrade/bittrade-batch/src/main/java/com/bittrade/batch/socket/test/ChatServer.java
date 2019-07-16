/**  
 * Project Name:bittrade-entrust  
 * File Name:Test.java  
 * Package Name:com.bittrade.entrust.machine  
 * DateTime: Jul 16, 2019 3:59:16 PM <br />
 * Copyright (c) 2019, 仙灵科技 All Rights Reserved.  
 *  
*/

package com.bittrade.batch.socket.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

/**
 * 即时通讯
 */
public class ChatServer extends WebSocketServer {

	public ChatServer(int port) throws UnknownHostException {
		super( new InetSocketAddress( port ) );
	}

	public ChatServer(InetSocketAddress address) {
		super( address );
	}

	/**
	 * 触发连接事件
	 */
	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
	}

	/**
	 * 触发关闭事件
	 */
	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		System.out.println( "com.bittrade.batch.socket.test.ChatServer.onClose(WebSocket, int, String, boolean)" );
//		userLeave( conn );
	}

	/**
	 * 客户端发送消息到服务器时触发事件
	 */
	@Override
	public void onMessage(WebSocket conn, String message) {
		ChatServerPool.sendMessage("message=" + message);
//		message = message.toString();
//		if (null != message && message.startsWith( "admin" )) {
//			this.userjoin( message.replaceFirst( "admin", "" ), conn );
//		}
//		if (null != message && message.startsWith( "LeaveAdmin" )) {
//			this.userLeave( conn );
//		}
//		if (null != message && message.contains( "admin886" )) {
//			String toUser = message.substring( message.indexOf( "admin886" ) + 8, message.indexOf( "admin888" ) );
//			message = message.substring( 0, message.indexOf( "admin886" ) ) + "[私信]  "
//					+ message.substring( message.indexOf( "admin888" ) + 8, message.length() );
//			ChatServerPool.sendMessageToUser( ChatServerPool.getWebSocketByUser( toUser ), message );// 向所某用户发送消息
//			ChatServerPool.sendMessageToUser( conn, message );// 同时向本人发送消息
//		} else {
//			ChatServerPool.sendMessage( message.toString() );// 向所有在线用户发送消息
//		}
	}

	public void onFragment(WebSocket conn, Framedata fragment) {
	}

	/**
	 * 触发异常事件
	 */
	@Override
	public void onError(WebSocket conn, Exception ex) {
		ex.printStackTrace();
		if (conn != null) {
			// some errors like port binding failed may not be assignable to a
			// specific websocket
		}
	}

	/**
	 * 用户加入处理
	 * 
	 * @param user
	 */
//	public void userjoin(String user, WebSocket conn) {
//		JSONObject result = new JSONObject();
//		result.element( "type", "user_join" );
//		result.element( "user", "<a οnclick=\"toUserMsg('" + user + "');\">" + user + "</a>" );
//		ChatServerPool.sendMessage( result.toString() ); // 把当前用户加入到所有在线用户列表中
//		String joinMsg = "{\"from\":\"[系统]\",\"content\":\"" + user + "上线了\",\"timestamp\":" + new Date().getTime() + ",\"type\":\"message\"}";
//		ChatServerPool.sendMessage( joinMsg ); // 向所有在线用户推送当前用户上线的消息
//		result = new JSONObject();
//		result.element( "type", "get_online_user" );
//		ChatServerPool.addUser( user, conn ); // 向连接池添加当前的连接对象
//		result.element( "list", ChatServerPool.getOnlineUser() );
//		ChatServerPool.sendMessageToUser( conn, result.toString() ); // 向当前连接发送当前在线用户的列表
//	}

	/**
	 * 用户下线处理
	 * 
	 * @param user
	 */
//	public void userLeave(WebSocket conn) {
//		String user = ChatServerPool.getUserByKey( conn );
//		boolean b = ChatServerPool.removeUser( conn ); // 在连接池中移除连接
//		if (b) {
//			JSONObject result = new JSONObject();
//			result.element( "type", "user_leave" );
//			result.element( "user", "<a οnclick=\"toUserMsg('" + user + "');\">" + user + "</a>" );
//			ChatServerPool.sendMessage( result.toString() ); // 把当前用户从所有在线用户列表中删除
//			String joinMsg = "{\"from\":\"[系统]\",\"content\":\"" + user + "下线了\",\"timestamp\":" + new Date().getTime() + ",\"type\":\"message\"}";
//			ChatServerPool.sendMessage( joinMsg ); // 向在线用户发送当前用户退出的消息
//		}
//	}

	public static void main(String[] args) throws InterruptedException, IOException {
		WebSocketImpl.DEBUG = false;
		ChatServer s = new ChatServer( new InetSocketAddress( "192.168.1.11", 7397 ) );
		s.start();
		System.out.println( "服务器的端口" + s.getPort() );
	}

	@Override
	public void onStart() {
		
	}

}

package com.bittrade.netty.handler;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.bittrade.netty.dto.TickerDto;
import com.bittrade.netty.dto.WebSocketParamDto;
import com.bittrade.netty.enums.WebSocketEnum.KlineEnum;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MyWebSocketServerHandler extends SimpleChannelInboundHandler<TickerDto> {

	private static final Logger			LOG					= LoggerFactory.getLogger( MyWebSocketServerHandler.class );

	private WebSocketServerHandshaker	handshaker;
	private int							lossConnectCount	= 0;

	/**
	 * channel 通道 action 活跃的
	 * 当客户端主动链接服务端的链接后，这个通道就是活跃的了。也就是客户端与服务端建立了通信通道并且可以传输数据
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		LOG.info( "客户端与服务端握手成功..." + ctx.channel().remoteAddress().toString() );
	}

	/**
	 * channel 通道 Inactive 不活跃的
	 * 当客户端主动断开服务端的链接后，这个通道就是不活跃的。也就是说客户端与服务端关闭了通信通道并且不可以传输数据
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		List<String> keys = Global.channelGroups.get( String.valueOf( ctx.channel().id() ) );
		if (null != keys) {
			for (String key : keys) {
				ChannelGroup channelGroup = Global.concurrentHashMap.get( key );
				if (channelGroup.size() > 0) {
					Global.concurrentHashMap.put( key, channelGroup );
				} else {
					Global.concurrentHashMap.remove( key );
				}
			}
			Global.channelGroups.remove( String.valueOf( ctx.channel().id() ) );
		}
		LOG.info( "客户端与服务端连接关闭：" + ctx.channel().remoteAddress().toString() );
	}

	/**
	 * channel 通道 Read 读取 Complete 完成 在通道读取完成后会在这个方法里通知，对应可以做刷新操作 ctx.flush()
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
		// 如果HTTP解码失败，返回HHTP异常
		if (!req.decoderResult().isSuccess() || (!"websocket".equals( req.headers().get( "Upgrade" ) ))) {
			sendHttpResponse( ctx, req, new DefaultFullHttpResponse( HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST ) );
			return;
		}
		// 获取url后置参数
		String uri = req.uri();
		// 构造握手响应返回，本机测试
		WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory( "ws://" + req.headers().get( "Host" ) + uri, null, false );
		handshaker = wsFactory.newHandshaker( req );
		if (handshaker == null) {
			WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse( ctx.channel() );
		} else {
			handshaker.handshake( ctx.channel(), req );
		}
		ctx.channel().writeAndFlush( new TextWebSocketFrame( "客户端与服务端握手成功..." ) );
	}

	private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
		// 返回应答给客户端
		if (res.status().code() != 200) {
			ByteBuf buf = Unpooled.copiedBuffer( res.status().toString(), CharsetUtil.UTF_8 );
			res.content().writeBytes( buf );
			buf.release();
		}
		// 如果是非Keep-Alive，关闭连接
		ChannelFuture f = ctx.channel().writeAndFlush( res );
		if (!HttpUtil.isKeepAlive( req ) || res.status().code() != 200) {
			f.addListener( ChannelFutureListener.CLOSE );
		}
	}

	/**
	 * exception 异常 Caught 抓住 抓住异常，当发生异常的时候，可以做一些相应的处理，比如打印日志、关闭链接
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		LOG.info( "已经30秒未收到客户端的消息了！" );
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent) evt;
			if (event.state() == IdleState.READER_IDLE) {
				lossConnectCount++;
				if (lossConnectCount > 2) {
					LOG.info( "关闭这个不活跃通道！" + ctx.channel() );
					ctx.channel().close();
				}
			}
		} else {
			super.userEventTriggered( ctx, evt );
		}
	}

	/**
	 * 接收客户端发送的消息 channel 通道 Read 读
	 * 简而言之就是从通道中读取数据，也就是服务端接收客户端发来的数据。但是这个数据在不进行解码时它是ByteBuf类型的
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		lossConnectCount = 0;
		if (msg instanceof FullHttpRequest) {
			handleHttpRequest( ctx, ((FullHttpRequest) msg) );
		} else if (msg instanceof WebSocketFrame) {
			LOG.info( handshaker.uri() );
			handlerWebSocketFrame( handshaker, ctx, (WebSocketFrame) msg );
		}
	}

	protected void handlerWebSocketFrame(WebSocketServerHandshaker handshaker, ChannelHandlerContext ctx, WebSocketFrame frame) {
		// 判断是否关闭链路的指令
		if (frame instanceof CloseWebSocketFrame) {
			handshaker.close( ctx.channel(), (CloseWebSocketFrame) frame.retain() );
			return;
		}
		// 判断是否ping消息
		if (frame instanceof PingWebSocketFrame) {
			LOG.info( "-------- if (frame instanceof PingWebSocketFrame) { --------" );
			ctx.channel().write( new PongWebSocketFrame( frame.content().retain() ) );
			return;
		}
		// 判断是否pong消息
		if (frame instanceof PongWebSocketFrame) {
			LOG.info( "-------- if (frame instanceof PongWebSocketFrame) { --------" );
//			ctx.channel().write( new PongWebSocketFrame( frame.content().retain() ) );
			return;
		}
		// 支持文本消息，不支持二进制消息
		if (!(frame instanceof TextWebSocketFrame)) {
			LOG.error( "仅支持文本消息，不支持二进制消息" );
			throw new UnsupportedOperationException( String.format( "%s frame types not supported", frame.getClass().getName() ) );
		}
		// 返回应答消息
		String message = ((TextWebSocketFrame) frame).text();
		LOG.info( "服务端收到：" + message );

		// 判断是否ping消息
		if ("ping".equals( message )) {
			ctx.channel().writeAndFlush( new TextWebSocketFrame( "pong" ) );
			return;
		}
		messageHandle( message, ctx );
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TickerDto msg) throws Exception {
		LOG.info( "msg:" + msg );
	}

	/**
	 * messageHandle:(消息处理). <br/>
	 * 
	 * @author Administrator
	 * @param message
	 * @param ctx
	 * @since JDK 1.8
	 */
	private void messageHandle(String message, ChannelHandlerContext ctx) {
		try {
			WebSocketParamDto webSocketParamDto = JSON.parseObject( message, WebSocketParamDto.class );
			String args = webSocketParamDto.getArgs();
			String symbol = args.split( "_" )[ 0 ]; // 交易对
			String granularity = args.split( "_" )[ 1 ]; // 时间粒度

			// 简单基础校验
			if (!Global.granularitys.contains( granularity ) || symbol.split( "-" ).length != 2) {
				failure( ctx, message );
				return;
			}
			String channelId = String.valueOf( ctx.channel().id() );
			List<String> ctxs = Global.channelGroups.get( channelId );
			ChannelGroup channelGroup = Global.concurrentHashMap.get( args );
			if (webSocketParamDto.getOp().equals( KlineEnum.SUBSCRIBE.getKey() )) {
				if (null == channelGroup) {
					channelGroup = new DefaultChannelGroup( GlobalEventExecutor.INSTANCE );
				}
				channelGroup.add( ctx.channel() );
				Global.concurrentHashMap.put( args, channelGroup );

				if (null == ctxs) {
					ctxs = new ArrayList<String>();
				}
				ctxs.add( args );
				Global.channelGroups.put( channelId, ctxs );
			} else if (webSocketParamDto.getOp().equals( KlineEnum.UNSUBSCRIBE.getKey() )) {
				channelGroup.remove( ctx.channel() );
				if (channelGroup.size() > 0) {
					Global.concurrentHashMap.put( args, channelGroup );
				} else {
					Global.concurrentHashMap.remove( args );
				}

				ctxs.remove( args );
				Global.channelGroups.put( channelId, ctxs );
			} else {
				failure( ctx, message );
			}
		} catch (Exception e) {
			LOG.error( "订阅格式异常" );
			failure( ctx, e.getMessage() );
		}
	}

	/**
	 * failure:(异常处理). <br/>
	 * 
	 * @author Administrator
	 * @param ctx
	 * @param message
	 * @since JDK 1.8
	 */
	private void failure(ChannelHandlerContext ctx, String message) {
		try {
			TextWebSocketFrame contws = new TextWebSocketFrame( "订阅格式错误：" + message );
			ctx.channel().writeAndFlush( contws );
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public static void _main(String[] args) {
		String symbol = "BTC-USDT";
		System.out.println( symbol.split( "-" ).length );
	}

}

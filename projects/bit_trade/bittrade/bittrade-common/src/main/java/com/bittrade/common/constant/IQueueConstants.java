package com.bittrade.common.constant;

/**
 * 消息队列的各种key
 * ClassName: IQueueConstants <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * DateTime: Jul 12, 2019 4:14:23 PM <br />
 *  
 * @author Administrator  
 * @version   
 * @since JDK 1.8
 */
public interface IQueueConstants {

//	// 消息交换器-**
//	String EXCHANGE__ = "exchange.direct.**";
//	// 消息队列-**
//	String QUEUE__ = "queue.**";
//	// 消息路由键-**
//	String ROUTE_KEY__ = "route.key.**";

	// 消息交换器-路由
	String EXCHANGE_DIRECT = "exchange.direct";
	// 消息交换器-主题
	String EXCHANGE_TOPIC = "exchange.topic";
	
	// 消息队列-K线
	String QUEUE__KLINE = "queue.kLine.60";
	// 消息路由键-K线
	String ROUTE_KEY__KLINE = "route.key.kLine.60";
	// 消息队列-成交
	String QUEUE__ENTRUST_RECORD = "queue.entrustRecord";
	// 消息路由键-成交
	String ROUTE_KEY__ENTRUST_RECORD = "route.key.entrustRecord";

}

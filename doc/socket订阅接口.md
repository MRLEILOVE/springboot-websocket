**WebSocket地址：ws://ip:port**



symbol：BTC-USDT，ETH-USDT，LTH-USDT，EOS-USDT

granularity：60,180,300,900,1800,3600,7200,14400,21600,43200,86400



**1、K线数据推送**

订阅：{"op":"subscribe","args":"symbol_granularity"}

取消：{"op":"unsubscribe","args":"symbol_granularity"}



如：{"op":"subscribe","args":"BTC-USDT_60"}



### 心跳消息

客户端必须每隔30秒给服务端发送ping的心跳消息，如果服务端30秒没有收到客户端的ping消息，服务端将主动断开与此客户端的连接，将不再给该客户端推送消息；服务端收到客户端发送的ping消息后立即返回pong，证明服务端已经收到心跳消息。


package com.liegq.www.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 自定义封装包含发送信息的WebSocket消息类
 * <p>
 * 创建人：yuanwl <br>
 * 创建时间：2018年8月10日 下午7:59:12 <br>
 * <p>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注： <br>
 * </p>
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WsMsg {

	/** 接收用户id，如果不为空，则表示发送给个人而不是广播 */
	private String userId;
    
	/**
     * 消息目的地。<br>
     * 发送广播消息，格式为：/topic/* ，*为任意路径，例如monitor，则可以发送给订阅地址为【/topic/monitor】的客户端；<br>
     * 发送私人消息，格式为：/*，*为任意路径，例如/1/message，客户端对应订阅地址：/user/{自定义客户端标识ID}/message？
     */
    private String distination;
    
    /** 实际发送的数据对象 */
    private Object data;
}
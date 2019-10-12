package com.liegq.www.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * WebSocket 工具类。用线程+队列的方式推送信息，可以实现异步推送，推送未完成前端也不会卡顿。<br>
 * <p>
 * 创建人：yuanwl <br>
 * 创建时间：2018年8月10日 下午7:56:18 <br>
 * <p>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注： <br>
 * </p>
 */
@Component
@Slf4j
public class WsUtil implements Runnable {

	private SimpMessagingTemplate messageingTemplate;
	private BlockingQueue<WsMsg> wsQueue = new LinkedBlockingDeque<>();

	@Autowired
	public WsUtil(SimpMessagingTemplate t) {
		log.info(">>>>>>>>>>>> 初始化WsUtil >>>>>>>>>>>>");
		this.messageingTemplate = t;
		new Thread(this).start();
	}

	/**
	 * 推送消息（实际上是把消息加到消息队列中，依次发送）
	 * <p>
	 * 创建人：yuanwl <br>
	 * 创建时间：2018年8月10日 下午8:29:13 <br>
	 * <p>
	 * 修改人： <br>
	 * 修改时间： <br>
	 * 修改备注： <br>
	 * </p>
	 *
	 * @param userId 用户id（唯一标识）
     * @param dest 消息目的地
     * @param data 消息内容
	 */
	public void pushMsg(String userId, String dest, Object data) {
		try {
			WsMsg msg = new WsMsg(userId, dest, data);
			wsQueue.put(msg);
		} catch (InterruptedException e) {
			log.error("添加实时消息异常", e);
		}
	}

	/**
	 * 推送消息（实际上是把消息加到消息队列中，依次发送）
	 * <p>
	 * 创建人：yuanwl <br>
	 * 创建时间：2018年8月10日 下午8:29:13 <br>
	 * <p>
	 * 修改人： <br>
	 * 修改时间： <br>
	 * 修改备注： <br>
	 * </p>
	 *
	 * @param msg
	 */
	public void pushMsg(WsMsg msg) {
		try {
			wsQueue.put(msg);
		} catch (InterruptedException e) {
			log.error("添加实时消息异常", e);
		}
	}

	/**
	 * （实际）发送消息
	 * <p>
	 * 创建人：yuanwl <br>
	 * 创建时间：2018年8月10日 下午8:29:38 <br>
	 * <p>
	 * 修改人： <br>
	 * 修改时间： <br>
	 * 修改备注： <br>
	 * </p>
	 *
	 * @param msg
	 */
	private void sendMsg(WsMsg msg) {
		if (StringUtils.isBlank(msg.getUserId())) {
			messageingTemplate.convertAndSend(msg.getDistination(), msg.getData());
		} else {
			messageingTemplate.convertAndSendToUser(msg.getUserId(), msg.getDistination(), msg.getData());
		}
	}

	@Override
	public void run() {
		log.info(">>>>>>>>>>>> 推送 WebSocket 消息线程启动，正在监听消息 >>>>>>>>>>>>");
		while (true) {
			try {
				WsMsg msg = wsQueue.take();
                sendMsg(msg);
            } catch (Exception e) {
				log.error("发送消息异常", e);
                break;
			}
		}
	}

}
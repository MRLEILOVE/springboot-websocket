package com.bittrade.c2c.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bittrade.c2c.vo.MessageVo;
import com.bittrade.c2c.vo.SendOrderVo;
import com.bittrade.c2c.vo.SendVo;
import com.bittrade.pojo.model.TChatRecordLog;

/**
 * @author xzc
 * @date 2019-08-19 20:34
 * @description
 */
public interface ChatRecordLogService extends IService<TChatRecordLog> {

    /**
     * 发送消息
     *
     * @param sendVo 消息实体
     */
    void sendMessage(SendVo sendVo);

    /**
     * 插入日志实体
     *
     * @param messageVo 消息内容
     */
    void saveMessage(MessageVo messageVo);

    /**
     * 推送订单信息
     *
     * @param sendOrderVo
     */
    void pullOrderDetail(SendOrderVo sendOrderVo);

    /**
     * 拉取与指定用户的聊天记录
     *
     * @param receiverId
     * @return 未读消息数
     */
    Long pullUnreadMessage(Long receiverId);

}

package com.bittrade.c2c.service.impl;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bittrade.c2c.dao.ChatRecordLogDAO;
import com.bittrade.c2c.service.ChatRecordLogService;
import com.bittrade.c2c.vo.MessageVo;
import com.bittrade.c2c.vo.SendOrderVo;
import com.bittrade.c2c.vo.SendVo;
import com.bittrade.pojo.model.ChatRecordLog;
import com.core.web.constant.entity.LoginUser;
import com.core.web.tool.WebUtil;
import com.google.common.base.Preconditions;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xzc
 * @date 2019-08-19 20:38
 * @description
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ChatRecordLogServiceImpl
        extends ServiceImpl<ChatRecordLogDAO, ChatRecordLog>
        implements ChatRecordLogService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ChatRecordLogService chatRecordLogService;

    @Autowired
    private SimpUserRegistry userRegistry;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 未读消息redis key
     */
    public static final String REDIS_UNREAD_MSG_KEY = "UNREAD_MSG:%s--%s";


    @Override
    public void sendMessage(SendVo sendVo) {
        //消息实体
        MessageVo messageVo = this.convertMessage(sendVo);
        Long senderId = messageVo.getSenderId();
        Preconditions.checkArgument(Objects.nonNull(senderId), "发送者不存在");
        //验证消息实体
        Long receiverId = messageVo.getReceiverId();
        Preconditions.checkArgument(Objects.nonNull(receiverId), "接收者不存在");
        //判断用户是否在线
        SimpUser simpUser = userRegistry.getUser(receiverId.toString());
        if (Objects.isNull(simpUser)) {
            //缓存消息实体到redis
            this.cacheUnreadMessage(messageVo);
        } else {
            //发送给接收者
            messagingTemplate.convertAndSendToUser(receiverId.toString(), StringUtils.EMPTY, messageVo);
        }
        log.info(String.format("用户未上线暂缓发送消息,接收消息的用户ID:%s", receiverId));

        //发送给发送者
        messagingTemplate.convertAndSendToUser(senderId.toString(), StringUtils.EMPTY, messageVo);
        //插入消息日志
        chatRecordLogService.saveMessage(messageVo);
    }

    /**
     * 缓存消息实体
     *
     * @param messageVo
     */
    private void cacheUnreadMessage(MessageVo messageVo) {
        Long senderId = messageVo.getSenderId();
        Long receiverId = messageVo.getReceiverId();
        String redisKey = String.format(REDIS_UNREAD_MSG_KEY, senderId, receiverId);
        BoundListOperations<Object, Object> messageList = redisTemplate.boundListOps(redisKey);
        messageList.leftPush(messageVo);
        messageList.expire(30, TimeUnit.DAYS);
    }

    /**
     * 实体转换
     *
     * @param sendVo 消息实体
     * @return
     */
    private MessageVo convertMessage(SendVo sendVo) {
        //获取当前用户
        LoginUser currentUser = WebUtil.getLoginUser();
        Long senderId = currentUser.getUser_id();
        String senderName = currentUser.getUser_name();
        Long receiverId = sendVo.getReceiverId();
        String receiverName = sendVo.getReceiverName();
        String content = sendVo.getContent();
        Integer sendType = sendVo.getSendType();
        //转换成消息实体
        MessageVo messageVo = new MessageVo();
        messageVo.setSenderName(senderName);
        messageVo.setSenderId(senderId);
        messageVo.setReceiverName(receiverName);
        messageVo.setReceiverId(receiverId);
        messageVo.setContent(content);
        messageVo.setMessageType(sendType);
        return messageVo;
    }

    @Override
    public void saveMessage(MessageVo messageVo) {
        Long senderId = messageVo.getSenderId();
        Long receiverId = messageVo.getReceiverId();
        String content = messageVo.getContent();
        Integer messageType = messageVo.getMessageType();
        ChatRecordLog chatRecordLog = new ChatRecordLog();
        chatRecordLog.setSenderId(senderId);
        chatRecordLog.setReceiverId(receiverId);
        chatRecordLog.setContent(content);
        chatRecordLog.setSendType(messageType);
        chatRecordLog.setRead(false);
        chatRecordLogService.save(chatRecordLog);
    }

    @Override
    public Long pullUnreadMessage(Long receiverId) {
        //获取当前用户
        LoginUser currentUser = WebUtil.getLoginUser();
        Long senderId = currentUser.getUser_id();
        String redisKey = String.format(REDIS_UNREAD_MSG_KEY, senderId, receiverId);
        BoundListOperations<Object, Object> unreadMsg = redisTemplate.boundListOps(redisKey);
        Long unreadCount = unreadMsg.size();
        for (int i = 0; i <= unreadCount; i++) {
            Object message = unreadMsg.rightPop();
            //推送消息
            messagingTemplate.convertAndSendToUser(receiverId.toString(), StringUtils.EMPTY, message);
        }
        return unreadCount;
    }

    @Override
    public void pullOrderDetail(SendOrderVo sendOrderVo) {
        //todo 推送订单信息 需要把订单信息缓存到redis 不管用户是否在线
    }
}

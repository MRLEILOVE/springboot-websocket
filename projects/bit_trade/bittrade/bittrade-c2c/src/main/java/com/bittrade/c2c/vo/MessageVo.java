package com.bittrade.c2c.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author xzc
 * @date 2019-08-19 17:33
 * @description 消息实体
 */

@Setter
@Getter
@ToString
public class MessageVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 发送者名字
     */
    private String senderName;

    /**
     * 发送者ID
     */
    private Long senderId;

    /**
     * 接受者名字
     */
    private String receiverName;

    /**
     * 接受者ID
     */
    private Long receiverId;

    /**
     * 内容
     */
    private String content;

    /**
     * 消息类型 1，系统消息 2，text消息 3，图片
     */
    private Byte messageType;


}

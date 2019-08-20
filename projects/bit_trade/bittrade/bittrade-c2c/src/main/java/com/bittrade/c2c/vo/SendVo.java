package com.bittrade.c2c.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author xzc
 * @date 2019-08-20 17:13
 * @description
 */
@Setter
@Getter
@ToString
public class SendVo {

    /**
     * 接收者user ID
     */
    private Long receiverId;

    /**
     * 接受者名字
     */
    private String receiverName;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息类型 1，系统消息 2，text消息 3，图片
     */
    private Integer sendType;

    /**
     * 发送时间
     */
    private LocalDateTime localDateTime = LocalDateTime.now();
}

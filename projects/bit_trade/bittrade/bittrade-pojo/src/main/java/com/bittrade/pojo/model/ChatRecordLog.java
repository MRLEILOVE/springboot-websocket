package com.bittrade.pojo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author xzc
 * @date 2019-08-19 18:02
 * @description
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value = "t_chat_record_log")
public class ChatRecordLog {

    /**
     * 日志id
     */
    private Long id;

    /**
     * 发送者ID
     */
    private Long senderId;

    /**
     * 接收者ID
     */
    private Long receiverId;

    /**
     * 内容
     */
    private String content;

    /**
     * 发送类型
     */
    private Integer sendType;

    /**
     * 是否已经看
     */
    private Boolean read;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}

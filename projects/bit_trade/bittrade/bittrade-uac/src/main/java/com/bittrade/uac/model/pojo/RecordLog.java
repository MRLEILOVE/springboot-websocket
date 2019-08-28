package com.bittrade.uac.model.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: xzc
 * @create: 2019/8/27 下午6:30
 * @description:
 **/
@Getter
@Setter
@ToString
@TableName("t_record_log")
public class RecordLog implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 设备类型
     */
    private Integer equipmentType;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 发送地址
     */
    private String equipmentMark;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}

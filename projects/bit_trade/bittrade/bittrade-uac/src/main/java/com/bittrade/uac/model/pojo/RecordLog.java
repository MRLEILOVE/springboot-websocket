package com.bittrade.uac.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 设备类型
     */
    @TableField("equipment_type")
    private Integer equipmentType;

    /**
     * 业务类型
     */
    @TableField("business_type")
    private String businessType;

    /**
     * 发送地址
     */
    @TableField("equipment_mark")
    private String equipmentMark;

    /**
     * 消息内容
     */
    @TableField("content")
    private String content;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;
}

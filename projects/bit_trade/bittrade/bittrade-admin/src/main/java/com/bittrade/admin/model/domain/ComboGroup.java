package com.bittrade.admin.model.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author ourblue
 * @since 2019-04-07
 */
@Data
@TableName("g_combo_group")
public class ComboGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 套餐表ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 周期（天）
     */
    private Integer days;
    /**
     *  最低价格（元）
     */
    @TableField("min_price")
    private BigDecimal minPrice;
    /**
     * 最高价格（元）
     */
    @TableField("max_price")
    private BigDecimal maxPrice;
    /**
     * 收益率（小数）
     */
    @TableField("earnings_ratio")
    private BigDecimal earningsRatio;
    /**
     * 开始时间
     */
    @TableField("start_time")
    private Integer startTime;
    /**
     * 结束时间
     */
    @TableField("end_time")
    private Integer endTime;

    /**
     * 级别
     */
    @TableField("level")
    private String level;

    /**
     * 狗狗名称
     */
    @TableField("dog_name")
    private String dogName;

    /**
     * 微分
     */
    @TableField("differential")
    private BigDecimal differential;

    /**
     * 狗狗的图片
     */
    @TableField("dog_img")
    private String dogImg;

    /**
     * 拆狗数量
     */
    @TableField("split_sum")
    private Integer splitSum;

    /**
     * 饱和度
     */
    @TableField("meet")
    private BigDecimal meet;
    /**
     * 套餐展示状态（1=未开始(预约)，2=已预约，3=进行中（领养），4=已过期（繁殖中））
     */
    @TableField(exist = false)
    private Integer stauts = 1;

    /**
     * 抢狗成功概率基数
     */
    @TableField("probability_base")
    private Integer probabilityBase;

    /**
     * 抢狗成功概率范围最小值
     */
    @TableField("probability_min")
    private Integer probabilityMin;

    /**
     * 抢狗成功概率范围最大值
     */
    @TableField("probability_max")
    private Integer probabilityMax;

    /**
     * 状态 0 已抢完  1 未抢完
     */
    @TableField(exist = false)
    private Integer type;

    /**
     * 后台设置剩余狗数量
     */
    @TableField(exist = false)
    private Integer betaSumSystem;
    /**
     * 后台设置预约人数
     */
    @TableField(exist = false)
    private Integer reserveSumSystem;
    /**
     * 剩余狗数量
     */
    @TableField(exist = false)
    private Integer betaSum;
    /**
     * 预约人数
     */
    @TableField(exist = false)
    private Integer reserveSum;

}

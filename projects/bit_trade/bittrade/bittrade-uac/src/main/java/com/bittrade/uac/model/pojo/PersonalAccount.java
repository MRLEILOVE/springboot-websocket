package com.bittrade.uac.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: xzc
 * @create: 2019-08-13 14:11
 * @description: c2c账户表
 **/
@TableName("c_personal_account")
@Data
public class PersonalAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 我的资产表ID
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
     * 关联用户ID
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 账户余额
     */
    private BigDecimal balance;
    /**
     * 冻结资产
     */
    @TableField("used_margin")
    private BigDecimal usedMargin;
    /**
     * 动态释放资产
     */
    @TableField("dynamic_balance")
    private BigDecimal dynamicBalance;
    /**
     * 总入金
     */
    @TableField("total_enter")
    private BigDecimal totalEnter;
    /**
     * 总出金
     */
    @TableField("total_exit")
    private BigDecimal totalExit;
    /**
     * 交易对类型（1=CNY/USDT，2=CNY/BI）
     */
    @TableField("product_type")
    private Integer productType;
    /**
     * 版本号
     */
    private Integer version;


}

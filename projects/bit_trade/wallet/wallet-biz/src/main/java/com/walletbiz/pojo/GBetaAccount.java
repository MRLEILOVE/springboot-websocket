package com.walletbiz.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 我的beta资产表
 * </p>
 *
 * @author helen
 * @since 2019-04-11
 */
@TableName("g_beta_account")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GBetaAccount implements Serializable {

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
     * 冻结资产
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
     * 钱包类型（0=USDT,1=BITT，2=时间链，3=β通证，4=推广收益,5=累计收益）
     */
    @TableField("product_type")
    private Integer productType;

    /**
     * 版本号
     */
    private Integer version;


}

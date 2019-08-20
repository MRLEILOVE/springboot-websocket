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
 * 钱包划转记录表
 * </p>
 *
 * @author ourblue
 * @since 2019-08-08
 */
@TableName("t_wallet_transfer")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TWalletTransfer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id",type = IdType.INPUT)
    private Long id;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 货币id
     */
    private Integer currency;
    /**
     * 数量
     */
    private BigDecimal count;
    /**
     * 业务流水号
     */
    @TableField("business_number")
    private String businessNumber;
    /**
     * 状态，1.待处理，2.成功，3.失败，4.状态未明
     */
    private Byte status;
    /**
     * 操作渠道：1.C2C钱包划转到币币钱包，2.币币钱包划转到C2C钱包
     */
    @TableField("type_channel")
    private Byte typeChannel;
    /**
     * 来源渠道：1.app，2.后台，3.PC
     */
    @TableField("source_channel")
    private Byte sourceChannel;
    /**
     * 描述
     */
    private String des;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
}

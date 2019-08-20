package com.walletbiz.pojo;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 提币钱包账单
 * </p>
 *
 * @author jobob
 * @since 2019-07-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("w_withdraw_wallet_bill")
public class WWithdrawWalletBill implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 默认角色
     */
    private String platform;

    /**
     * 订单号
     */
    @TableField("order_id")
    private String orderId;

    /**
     * 1:进账，-1出账
     */
    private Integer direction;

    /**
     * 币种大类:BTC系列、BTC_TOKEN、EOS系列、ETH、ETH_TOKEN
     */
    @TableField("coin_type")
    private String coinType;

    /**
     * 币种  BTC、USDT、EOS、ETH等
     */
    private String token;

    /**
     * 合约地址USDT=31
     */
    private String contract;

    /**
     * 发送地址
     */
    @TableField("sender_address")
    private String senderAddress;

    /**
     * 接收者地址
     */
    @TableField("receiver_address")
    private String receiverAddress;

    /**
     * 对应coin转账数量
     */
    private BigDecimal amount;


    /**
     * 打包区块
     */
    private Long block;

    /**
     * 交易hash
     */
    private String tx;

    /**
     * 交易步骤： 10待审核 20待转账 25在转账 30已发布 40确认中 50确认完成
     */
    @TableField("trade_step")
    private String tradeStep;

    /**
     * 操作类型
     */
    @TableField("operation_type")
    private String operationType;

    /**
     * 转移状态：0：初始化，1：待归集，2已转移，3不用处理
     */
    @TableField("transfer_flag")
    private Integer transferFlag;

    /**
     * 0：初始化，1：未同步到资金账户，2已同步到资金账户，3不用处理
     */
    private Integer flag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;


}

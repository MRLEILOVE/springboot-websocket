package com.wallet.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.math.BigInteger;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 提币钱包账单
 * </p>
 *
 * @author xxx
 * @since 2019-06-27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("w_withdraw_wallet_bill")
public class WithdrawWalletBill extends Model<WithdrawWalletBill> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 默认角色
     */
    private String platform;

    /**
     * 订单号
     */
    private String orderId;

    /**
     * 1:进账，-1出账
     */
    private Integer direction;

    /**
     * 币种大类:BTC系列、BTC_TOKEN、EOS系列、ETH、ETH_TOKEN
     */
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
    private String senderAddress;

    /**
     * 接收者地址
     */
    private String receiverAddress;

    /**
     * 对应coin转账数量
     */
    private BigDecimal amount;

    /**
     * 打包区块
     */
    private BigInteger block;

    /**
     * 交易hash
     */
    private String tx;

    /**
     * 交易步骤： 10待审核 20待转账 25在转账 30已发布 40确认中 50确认完成
     */
    private String tradeStep;

    /**
     * 操作类型
     */
    private String operationType;

    /**
     * 转移状态：0：初始化，1：待归集，2已转移，3不用处理
     */
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
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}

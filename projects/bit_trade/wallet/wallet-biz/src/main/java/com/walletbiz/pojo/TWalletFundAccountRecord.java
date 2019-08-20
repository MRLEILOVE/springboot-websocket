package com.walletbiz.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_wallet_fund_account_record")
public class TWalletFundAccountRecord implements Serializable {
    private Integer id;

    @TableField("user_id")
    private Long userId;

    private String currency;


    //成交前金额
    @TableField("`before_amount`")
    private BigDecimal beforeAmount;

    //成交后金额
    @TableField("after_amount")
    private BigDecimal afterAmount;

    //变动金额
    @TableField("change_amount")
    private BigDecimal changeAmount;

    //成交类型,1-充值 2-提现 3-划转：资金账户-->法币钱包 4-划转：法币钱包-->资金账户
    private Integer type;

    @TableField("create_time")
    private Date createTime;

    @TableField("order_id")
    private String orderId;

}

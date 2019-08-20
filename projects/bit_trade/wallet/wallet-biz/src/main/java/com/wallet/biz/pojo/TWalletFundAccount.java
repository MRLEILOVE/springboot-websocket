package com.wallet.biz.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@TableName("t_wallet_fund_account")
@AllArgsConstructor
@NoArgsConstructor
public class TWalletFundAccount implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @TableField("user_id")
    private Long userId;
    private String currency;
    private BigDecimal total;
    @TableField("transfer_frozen")
    private BigDecimal transferFrozen;
    @Version
    private Integer version;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;
}

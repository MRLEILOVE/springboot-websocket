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
 * <p>
 *
 * </p>
 *
 * @author helen
 * @since 2018-12-15
 */
@TableName("t_user_account")
@Data
public class UserAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 用户名
     */
    @TableField("login_name")
    private String loginName;
    /**
     * 姓名
     */
    @TableField("real_name")
    private String realName;
    /**
     * 证件审核：0未提交，1通过，2审核中，3审核拒绝'
     */
    @TableField("fhas_real_Validate")
    private Integer fhasRealValidate;
    /**
     * 账户余额
     */
    private BigDecimal balance;
    /**
     * 体验金余额
     */
    @TableField("balance_token")
    private BigDecimal balanceToken;
    /**
     * 冻结金额(提现)
     */
    @TableField("frozen_amount")
    private BigDecimal frozenAmount;
    /**
     * 总入金(充值)
     */
    @TableField("total_deposits")
    private BigDecimal totalDeposits;
    /**
     * 体验金总充值
     */
    @TableField("total_recharge_token")
    private BigDecimal totalRechargeToken;
    /**
     * 总出金(提现)
     */
    @TableField("total_withdrawals")
    private BigDecimal totalWithdrawals;
    /**
     * 占用保证金(下单冻结)
     */
    @TableField("used_margin")
    private BigDecimal usedMargin;
    /**
     * 体验金下单冻结
     */
    @TableField("used_token")
    private BigDecimal usedToken;
    /**
     * 体验金剩余使用次数
     */
    @TableField("token_number")
    private Integer tokenNumber;
    /**
     * 是否内部账户 1=正常账户、2=内部账户
     */
    @TableField("internal_account")
    private Integer internalAccount;
    /**
     * 版本号，每更新一次数据加1
     */
    private Integer version;
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

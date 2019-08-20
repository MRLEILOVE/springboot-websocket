package com.wallet.biz.pojo;

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
 * 我的beta资产流水表
 * </p>
 *
 * @author helen
 * @since 2019-04-15
 */
@TableName("g_beta_account_record")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GBetaAccountRecord implements Serializable {

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
     * 关联用户ID
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 流水号
     */
    @TableField("record_number")
    private String recordNumber;
    /**
     * 关联个人资产表ID
     */
    @TableField("beta_account_id")
    private Integer betaAccountId;
    /**
     * 交易前金额
     */
    @TableField("before_amount")
    private BigDecimal beforeAmount;
    /**
     * 交易金额
     */
    private BigDecimal amount;
    /**
     * 交易后金额
     */
    @TableField("after_amount")
    private BigDecimal afterAmount;
    /**
     * 备注
     */
    private String remark;
    /**
     * 流水类型1:划转法币-->beta账户，2：beta账户-->划转法币，3：划转法币-->资金账户，4：划转资金账户-->划转法币'
     */
    @TableField("record_type")
    private Integer recordType;

}

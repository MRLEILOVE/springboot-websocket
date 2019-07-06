package com.bittrade.pojo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 虚拟币钱包表日志表
 * </p>
 *
 * @author jobob
 * @since 2019-07-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TWalletRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 货币id(t_currency.id)
     */
    private Integer currencyId;

    /**
     * 成交前金额
     */
    private BigDecimal beforeAmount;

    /**
     * 成交后金额
     */
    private BigDecimal afterAmount;

    /**
     * 变动金额
     */
    private BigDecimal changeAmount;

    /**
     * 成交类型,1-充值 2-提现 3-币币交易 4-划转：币币钱包-->法币钱包 5-划转：法币钱包-->币币钱包
     */
    private Boolean type;

    /**
     * 关联的id,当前主要指成交id,t_entrust_record.id
     */
    private Long entrustRecordId;

    /**
     * 版本号，每更新一次数据加1
     */
    private Integer version;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}

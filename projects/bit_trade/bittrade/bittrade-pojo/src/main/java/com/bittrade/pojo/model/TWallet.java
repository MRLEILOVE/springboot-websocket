package com.bittrade.pojo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 虚拟币钱包表
 * </p>
 *
 * @author jobob
 * @since 2019-07-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TWallet implements Serializable {

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
     * 总数量
     */
    private BigDecimal total;

    /**
     * 交易冻结数量
     */
    private BigDecimal tradeFrozen;

    /**
     * 划转冻结数量
     */
    private BigDecimal transferFrozen;

    /**
     * 版本号，每更新一次数据加1
     */
    private Integer version;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}

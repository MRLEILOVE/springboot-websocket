package com.wallet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * <p>
 *
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
@TableName("w_coin_config")
public class CoinConfig extends Model<CoinConfig> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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
     * 扫描区块的高度（主要查充值数据）
     */
    private BigInteger scanBlock;

    /**
     * 基础倍数8【基础单位换成聪】ETH是18，有些ETHtoken不是18.需要注意
     */
    private Integer baseMultiple;

    /**
     * 最低确认数
     */
    private BigInteger minConfirm;

    /**
     * boss钱包
     */
    private String bossAddress;

    /**
     * 最低归集数
     */
    private BigDecimal minCollectionAmount;

    /**
     * 禁用D  启用E
     */
    private String valid;

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

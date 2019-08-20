package com.wallet.chain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 配置钱包
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
@TableName("w_config_wallet")
public class ConfigWallet extends Model<ConfigWallet> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 币种大类
     */
    private String coinType;

    /**
     * 钱包类型:withdarw[提币地址]fee[手续费地址]collect[归集]
     */
    private String walletType;

    /**
     * 地址
     */
    private String address;

    /**
     * keystore
     */
    private String keystore;

    /**
     * 是否有效：D无效E有效
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

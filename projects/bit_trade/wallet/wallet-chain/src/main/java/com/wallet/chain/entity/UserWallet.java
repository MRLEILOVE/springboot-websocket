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
 * 钱包
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
@TableName("w_user_wallet")
public class UserWallet extends Model<UserWallet> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 默认角色
     */
    private String platform;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 币种大类
     */
    private String coinType;

    /**
     * 地址
     */
    private String address;

    /**
     * 私钥
     */
    private String privateKey;

    /**
     * 是否需要处理  0:未处理   1已处理
     */
    private Boolean flag;

    /**
     * 是否有效：D无效E有效
     */
    private String valid;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}

package com.bittrade.pojo.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.core.framework.base.model.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 钱包
 * </p>
 *
 * @author jobob
 * @since 2019-07-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WUserWallet extends BaseModel<WUserWallet> implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 是否需要处理  0:否   1是
     */
    private Boolean flag;

    /**
     * 是否有效：D无效E有效
     */
    private String valid;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}

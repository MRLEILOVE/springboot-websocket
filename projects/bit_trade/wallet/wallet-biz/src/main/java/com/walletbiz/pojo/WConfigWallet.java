package com.walletbiz.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("配置钱包")
@TableName("w_config_wallet")
public class WConfigWallet implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("币种大类")
    @TableField("coin_type")
    private String coinType;

    @ApiModelProperty("钱包类型:withdarw[提币地址]fee[手续费地址]collect[归集]")
    @TableField("wallet_type")
    private String walletType;

    @ApiModelProperty("地址")
    @TableField("address")
    private String address;

    @ApiModelProperty("keystore")
    @TableField("keystore")
    private String keystore;

    @ApiModelProperty("是否有效：D无效E有效")
    @TableField("valid")
    private Byte valid;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private Date updateTime;
}

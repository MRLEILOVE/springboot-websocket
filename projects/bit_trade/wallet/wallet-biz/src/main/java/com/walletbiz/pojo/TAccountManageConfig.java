package com.walletbiz.pojo;

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
@TableName("t_account_manage_config")
@ApiModel("币种-钱包关联表")
public class TAccountManageConfig {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("sort")
    private Integer sort;

    @ApiModelProperty("账户id")
    @TableField("account_id")
    private Integer accountId;

    @ApiModelProperty("币种id")
    @TableField("account_config_id")
    private Integer accountConfigId;
}

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
@TableName("t_account_config")
@ApiModel("币种配置表")
public class TAccountConfig implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty("中文名称")
    private String name;

    @ApiModelProperty("关键字")
    private String keyword;

    @ApiModelProperty("值")
    private String value;

    @ApiModelProperty("钱包类型")
    @TableField("account_type")
    private Integer accountType;

    @ApiModelProperty("是否可以充币(1=是2=否)")
    @TableField("top_up_coin")
    private Integer topUpCoin;

    @ApiModelProperty("是否可以提币(1=是2=否)'")
    @TableField("draw_coin")
    private Integer drawCoin;

    @ApiModelProperty("是否可以修改(1=是2=否)")
    @TableField("update_flag")
    private Integer updateFlag;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("排序")
    private Integer sort;

}

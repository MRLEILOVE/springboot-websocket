package com.walletbiz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel("资金划转dto")
@Data
public class TransferDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id（前端不用传，后端直接获取）")
    private Long userId;
    @ApiModelProperty("币种")
    private String currency;
    @ApiModelProperty("账户(转入)")
    private Integer accountInId;
    @ApiModelProperty("账户(转出)")
    private Integer accountOutId;
    @ApiModelProperty("数量")
    private BigDecimal num;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("划转类型")
    private Integer type;
}

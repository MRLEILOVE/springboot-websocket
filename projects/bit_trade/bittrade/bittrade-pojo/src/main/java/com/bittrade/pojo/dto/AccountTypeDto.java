package com.bittrade.pojo.dto;

import java.util.Date;

import com.core.framework.base.DTO.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("账户类型分页dto")
@Data
public class AccountTypeDto  extends BaseDTO<AccountTypeDto> {
    @ApiModelProperty("类型")
    private Integer type;

    @ApiModelProperty("币种id")
    private Integer currencyId;

    @ApiModelProperty("开始时间")
    private Date beginTime;

    @ApiModelProperty("结束时间")
    private Date endTime;
}

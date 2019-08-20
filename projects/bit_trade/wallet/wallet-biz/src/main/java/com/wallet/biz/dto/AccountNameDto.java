package com.wallet.biz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("账户名字dto")
public class AccountNameDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("账户名称")
    private String name;
}

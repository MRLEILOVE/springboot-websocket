package com.bittrade.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("分页对象")
public class UserPageDTO {
    @ApiModelProperty(value = "当前页")
    private Integer current;

    @ApiModelProperty(value = "页大小")
    private Integer size;

    @ApiModelProperty("用户id")
    private String userId;
}

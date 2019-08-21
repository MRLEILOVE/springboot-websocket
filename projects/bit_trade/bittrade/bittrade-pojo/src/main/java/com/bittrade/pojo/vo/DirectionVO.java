package com.bittrade.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DirectionVO {
    @ApiModelProperty("账户名称")
    private String accountName1;
    @ApiModelProperty("账户名称")
    private String accountId1;
    @ApiModelProperty("账户名称")
    private String accountName2;
    @ApiModelProperty("账户名称")
    private String accountId3;
    @ApiModelProperty("状态")
    private Integer status;
}

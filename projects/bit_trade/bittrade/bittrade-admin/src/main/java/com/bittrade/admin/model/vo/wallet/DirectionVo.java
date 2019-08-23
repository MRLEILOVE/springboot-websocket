package com.bittrade.admin.model.vo.wallet;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DirectionVo {
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("源账户")
    private String mainAccount;

    @ApiModelProperty("目标账户")
    private String targetAccount;

    @ApiModelProperty("是否可划转(0禁用，1启用)")
    private Integer status;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}

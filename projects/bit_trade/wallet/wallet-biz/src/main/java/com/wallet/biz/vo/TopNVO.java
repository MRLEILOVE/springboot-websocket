package com.wallet.biz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("topN对象")
public class TopNVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("账户 1：资金账户  2：beta账户  3：法币账户")
    private String account;

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("类型")
    private String type;
}

package com.bittrade.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("资产vo")
public class AssetsVO {
    @ApiModelProperty("可用总数量")
    private BigDecimal total;

    @ApiModelProperty("总冻结数量")
    private BigDecimal totalFrozen;

    @ApiModelProperty("交易冻结数量")
    private BigDecimal tradeFrozen;

    @ApiModelProperty("划转冻结数量")
    private BigDecimal transferFrozen;

    @ApiModelProperty("货币id")
    private Long currencyId;

    @ApiModelProperty("货币名字")
    private String currencyName;
}

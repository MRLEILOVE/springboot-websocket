package com.bittrade.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("币种")
public class CoinTypeVO {

    private static final long serialVersionUID = 1L;

 //   @NotNull(message = "币种类型不能为空")
    @ApiModelProperty("币种类型")
    private String coinType;

//    @NotNull(message = "具体币种不能为空")
    @ApiModelProperty("具体币种")
    private String token;
}



package com.bittrade.pojo.vo;

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
@ApiModel("资金记录VO")
public class AssetRecordTypeVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("类型")
    private Integer type;

    @ApiModelProperty("显示文案")
    private String name;

}

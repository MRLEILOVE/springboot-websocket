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
@ApiModel("资金记录VO")
public class AssetRecordTypeVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("类型  1:提币 2：充值 3：转入资金账户 4：转出资金账户  5：转入法币账户  6：转出法币账户  7：转入beta账户  8：转出beta账户")
    private Integer type;

    @ApiModelProperty("显示文案")
    private String name;

}

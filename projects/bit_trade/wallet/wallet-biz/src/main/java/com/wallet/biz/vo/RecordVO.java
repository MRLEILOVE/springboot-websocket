package com.wallet.biz.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("币种")
    private String currency;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;

    @ApiModelProperty("变动金额(变动前金额 - 变动后金额)")
    private BigDecimal changeAmount;

    @ApiModelProperty("余额（变动后的金额）")
    private BigDecimal afterAmount;

    @ApiModelProperty("手续费")
    private BigDecimal fee;

    @ApiModelProperty("订单id")
    private String orderId;
}

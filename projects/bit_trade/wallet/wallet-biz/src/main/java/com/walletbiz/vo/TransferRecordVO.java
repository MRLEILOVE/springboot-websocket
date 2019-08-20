package com.walletbiz.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel("划转记录")
public class TransferRecordVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("账户类型(入账)")
    private String accountIn;

    @ApiModelProperty("账户类型(出账)")
    private String accountOut;

    @ApiModelProperty("划转类型")
    private Integer type;

    @ApiModelProperty("变动金额")
    private BigDecimal changeAmount;

    @ApiModelProperty("转账时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;

    @ApiModelProperty("币种类型")
    private String currency;

    @ApiModelProperty("备注")
    private String remark;
}

package com.wallet.chain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OmniTransactionResultDto {

    private String txid;//交易ID
    private BigDecimal fee;//手续费
    private String sendingaddress;//发送地址
    private String referenceaddress;//接收地址
    private String type;//交易类型
    private Integer propertyid;//合约编号
    private BigDecimal amount;//交易数额
    private Boolean valid;//交易是否正常
    private Date blocktime;//区块时间
    private BigInteger block;//打包区块高度
    private BigInteger confirmations;//确认区块
}

package com.wallet.chain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 交易详情
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BTCTransactionDto {

    private String account;//接收账号
    private String address;//接收地址
    private String category;//操作类型
    private BigDecimal amount;//交易数额
    private BigInteger confirmations;//确认区块数
    private String txid;//交易ID
    private String blockhash;//区块hash
}

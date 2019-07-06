package com.coin.wallet.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PointUnSpendDto {

    private String txid;
    private Integer vout;
    private String scriptPubKey;
    private BigDecimal amount;
    private BigInteger confirmations;
}

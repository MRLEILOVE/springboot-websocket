package com.wallet.chain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RawtransactionDto {
    private String txid;
    private String blockhash;
    private BigInteger confirmations;
    private List<VinDto> vin;
    private List<VoutDto> vout;
}

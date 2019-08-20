package com.wallet.chain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnspentOutputDto {

    private String tx_hash;
    private String tx_hash_big_endian;
    private String tx_index;
    private Integer tx_output_n;
    private String script;
    private Long value;
    private String value_hex;
    private BigInteger confirmations;

}

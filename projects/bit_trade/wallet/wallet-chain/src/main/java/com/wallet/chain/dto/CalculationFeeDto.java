package com.wallet.chain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bitcoinj.core.UTXO;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalculationFeeDto {

    private Long fee;
    private Long utxoSum;
    private List<UTXO> senderUnSpend;
}

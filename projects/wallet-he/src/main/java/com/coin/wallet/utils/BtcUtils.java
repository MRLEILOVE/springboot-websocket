package com.coin.wallet.utils;

import com.coin.wallet.dto.CalculationFeeDto;
import com.coin.wallet.exception.CommonException;
import org.bitcoinj.core.UTXO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class BtcUtils {

    public static CalculationFeeDto calculationFee(List<UTXO> willUseing, Long feeRate,BigDecimal btcAmount) {
        long utxoAmount = 0L;
        long fee = 0L;
        long utxoSize = 0L;
        long baseBtcAmount = BigDecimal.TEN.pow(8).multiply(btcAmount).longValue();

        List<UTXO> senderUnSpend = new ArrayList<>();

        for (UTXO output : willUseing) {
            if (utxoAmount > (fee + baseBtcAmount)) {
                break;
            } else {
                senderUnSpend.add(output);
                utxoSize++;
                utxoAmount += output.getValue().getValue();
                fee = (utxoSize * 148 + 34 * 3 + 10) * feeRate;
            }
        }

        return CalculationFeeDto.builder()
                .fee(fee)
                .utxoSum(utxoAmount)
                .senderUnSpend(senderUnSpend)
                .build();
    }

    public static String handleUSDTHex(BigDecimal amount, String contract) {
        return "6a146f6d6e69" + String.format("%016x", Long.valueOf(contract)) + String.format("%016x", BigDecimal.TEN.pow(8).multiply(amount).longValue());
    }

}

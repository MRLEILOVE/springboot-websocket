package com.wallet.chain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeeRateDto {

    private Long fastestFee = 35L;
    private Long halfHourFee = 30L;
    private Long hourFee = 10L;
}

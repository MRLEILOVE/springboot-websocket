package com.wallet.biz.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAddressResultDto {

    private Long userId;
    private String coinType;
    private String address;
}

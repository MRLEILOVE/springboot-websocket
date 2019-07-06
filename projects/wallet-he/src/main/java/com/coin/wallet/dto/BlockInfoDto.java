package com.coin.wallet.dto;

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
public class BlockInfoDto {

    private BigInteger height;//当前高度
    private String nextblockhash;//如果是最新的块不存在下一个
    private List<String> tx;
}

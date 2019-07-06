package com.bittrade.pojo.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 币币账户VO
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CoinAccountVO {
    /**
     * udst总数量
     */
    private Double total;

    /**
     * 折合人民币
     */
    private Double CNY;

    /**
     * 钱包列表
     */
    List<QueryWalletVO> walletVOS;
}

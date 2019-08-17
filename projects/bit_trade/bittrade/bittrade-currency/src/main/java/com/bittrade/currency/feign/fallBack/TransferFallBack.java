package com.bittrade.currency.feign.fallBack;

import com.bittrade.currency.feign.ITransferFeignService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class TransferFallBack implements ITransferFeignService {
    @Override
    public String accountEntry(Long userId, String currency, BigDecimal num,Integer type) {
        return "timeOut";
    }

    /**
     * 获取划转类型
     * @param accountInId 入账钱包id
     * @param accountOutId 出账钱包id
     * @return
     */
    @Override
    public Integer getTypeFeign(Integer accountInId, Integer accountOutId) {
        return null;
    }
}

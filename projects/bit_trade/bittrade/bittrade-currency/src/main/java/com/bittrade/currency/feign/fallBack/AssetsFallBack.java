package com.bittrade.currency.feign.fallBack;

import com.bittrade.currency.feign.AssetsService;
import com.bittrade.pojo.vo.ConversionVo;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AssetsFallBack implements AssetsService {
    @Override
    public String getAssets(Long userId) {
        return "0";
    }

    @Override
    public ConversionVo personalTotalConversion(Long userId) {
        return ConversionVo.builder().USDT(BigDecimal.ZERO).CNY(BigDecimal.ZERO).build();
    }
}

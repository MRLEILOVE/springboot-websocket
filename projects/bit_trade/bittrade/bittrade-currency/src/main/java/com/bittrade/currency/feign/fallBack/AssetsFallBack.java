package com.bittrade.currency.feign.fallBack;

import com.bittrade.currency.feign.AssetsService;
import org.springframework.stereotype.Component;

@Component
public class AssetsFallBack implements AssetsService {
    @Override
    public String getAssets(Long userId) {
        return "0";
    }
}

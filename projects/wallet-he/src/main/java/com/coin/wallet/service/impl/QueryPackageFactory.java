package com.coin.wallet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coin.wallet.entity.CoinConfig;
import com.coin.wallet.enums.CoinTypeEnum;
import com.coin.wallet.service.CoinConfigService;
import com.coin.wallet.service.IQueryPackageFactory;
import com.coin.wallet.service.IQueryPackageStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class QueryPackageFactory implements IQueryPackageFactory {


    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private CoinConfigService coinConfigService;

    @Override
    public void execute() {
        for (CoinTypeEnum coinTypeEnum : CoinTypeEnum.values()) {
            List<CoinConfig> list = coinConfigService.list(new QueryWrapper<>(CoinConfig.builder()
                    .coinType(coinTypeEnum.getCoinType())
                    .valid("E")
                    .build()));
            for (CoinConfig coinConfig : list) {
                try {
                    IQueryPackageStrategy queryPackageStrategy = applicationContext.getBean("queryPackageStrategy" + coinTypeEnum.getCoinType(), IQueryPackageStrategy.class);
                    queryPackageStrategy.execute(coinConfig);
                } catch (Exception e) {
                    log.error("QueryPackageFactory.execute", e);
                }
            }
        }
    }
}

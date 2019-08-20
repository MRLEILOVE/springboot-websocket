package com.wallet.chain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wallet.chain.entity.CoinConfig;
import com.wallet.chain.enums.CoinTypeEnum;
import com.wallet.chain.service.CoinConfigService;
import com.wallet.chain.service.ICollectionFactory;
import com.wallet.chain.service.ICollectionStrategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CollectionFactory implements ICollectionFactory {
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
                    ICollectionStrategy strategy = applicationContext.getBean("collectionStrategy" + coinTypeEnum.getCoinType(), ICollectionStrategy.class);
                    strategy.execute(coinConfig);
                } catch (Exception e) {
                    log.error("CollectionFactory.execute", e);
                }
            }
        }
    }
}

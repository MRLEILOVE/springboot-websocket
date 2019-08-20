package com.wallet.chain.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wallet.chain.service.ICollectionFactory;
import com.wallet.chain.utils.IdUtils;

/**
 * 归集
 */
@Slf4j
@Component
public class CollectionJob {

    @Autowired
    private ICollectionFactory factory;

    @Scheduled(cron = "0/5 * * * * ?")
    public void execute() {

        String batch = IdUtils.uuid();
        try {
            log.info("CollectionJob.execute 归集 开始===={}", batch);
            factory.execute();
            log.info("CollectionJob.execute 归集 结束===={}", batch);
        } catch (Exception e) {
            log.error("CollectionJob.execute 归集 异常===={}", batch, e);
        }
    }
}

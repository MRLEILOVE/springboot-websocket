package com.wallet.chain.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wallet.chain.service.IRechargeScanFactory;
import com.wallet.chain.utils.IdUtils;

/**
 * 充值数据扫描
 */
@Slf4j
@Component
public class RechargeBillScanJob {

    @Autowired
    private IRechargeScanFactory factory;

    @Scheduled(cron = "0/5 * * * * ?")
    public void execute() {

        String batch = IdUtils.uuid();
        try {
            log.info("RechargeBillScanJob.execute 充值数据扫描 开始===={}", batch);
            factory.execute();
            log.info("RechargeBillScanJob.execute 充值数据扫描 结束===={}", batch);
        } catch (Exception e) {
            log.error("RechargeBillScanJob.execute 充值数据扫描 异常===={}", batch, e);
        }
    }

}

package com.coin.wallet.job;

import com.coin.wallet.service.IQueryPackageFactory;
import com.coin.wallet.utils.IdUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 查询打包高度
 */
@Slf4j
@Component
public class QueryPackageJob {

    @Autowired
    private IQueryPackageFactory factory;

    @Scheduled(cron = "0/5 * * * * ?")
    public void execute() {
        String batch = IdUtils.uuid();
        try {
            log.info("QueryPackageJob.execute 查询打包高度 开始===={}", batch);
            factory.execute();
            log.info("RechargeBillScanJob.execute 查询打包高度 结束===={}", batch);
        } catch (Exception e) {
            log.error("QueryPackageJob.execute 查询打包高度 异常===={}", batch, e);
        }
    }

}

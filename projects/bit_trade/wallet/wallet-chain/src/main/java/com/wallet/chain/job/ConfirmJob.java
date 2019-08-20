package com.wallet.chain.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wallet.chain.service.IConfirmFactory;
import com.wallet.chain.utils.IdUtils;

/**
 * 确认高度
 */
@Slf4j
@Component
public class ConfirmJob {

    @Autowired
    private IConfirmFactory factory;

    @Scheduled(cron = "0/5 * * * * ?")
    public void execute() {

        String batch = IdUtils.uuid();
        try {
            log.info("ConfirmJob.execute 确认高度 开始===={}", batch);
            factory.execute();
            log.info("ConfirmJob.execute 确认高度 结束===={}", batch);
        } catch (Exception e) {
            log.error("ConfirmJob.execute 确认高度 异常===={}", batch, e);
        }
    }
}

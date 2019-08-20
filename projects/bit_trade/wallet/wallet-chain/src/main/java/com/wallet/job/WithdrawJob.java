package com.wallet.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wallet.service.IWithdrawFactory;
import com.wallet.utils.IdUtils;

/**
 * 提币交易发送到区块链中
 */
@Slf4j
@Component
public class WithdrawJob {

    @Autowired
    private IWithdrawFactory factory;

    @Scheduled(cron = "0/5 * * * * ?")
    public void execute() {

        String batch = IdUtils.uuid();
        try {
            log.info("WithdrawJob.execute 提币 开始===={}", batch);
            factory.execute();
            log.info("WithdrawJob.execute 提币 结束===={}", batch);
        } catch (Exception e) {
            log.error("WithdrawJob.execute 提币 异常===={}", batch, e);
        }
    }
}

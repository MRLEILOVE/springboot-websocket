package com.coin.wallet.job;

import com.coin.wallet.service.IWithdrawFactory;
import com.coin.wallet.utils.IdUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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

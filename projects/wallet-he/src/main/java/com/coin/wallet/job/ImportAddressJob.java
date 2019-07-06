package com.coin.wallet.job;

import com.coin.wallet.service.UserWalletService;
import com.coin.wallet.utils.IdUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 把钱包地址导入钱包节点【btc，usdt】
 */
@Slf4j
@Component
public class ImportAddressJob {

    @Autowired
    private UserWalletService userWalletService;

    @Scheduled(cron = "0/5 * * * * ?")
    public void execute() {

        String batch = IdUtils.uuid();
        try {
            log.info("ImportAddressJob.execute usdt,btc把钱包导入节点 开始===={}", batch);
            userWalletService.importAddress();
            log.info("ImportAddressJob.execute usdt,btc把钱包导入节点 结束===={}", batch);
        } catch (Exception e) {
            log.error("ImportAddressJob.execute usdt,btc把钱包导入节点 异常===={}", batch, e);
        }
    }

}

package com.wallet.biz.scheduled;

import com.wallet.biz.api.service.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


//链--->>资金账户同步定时任务
@Slf4j
@Component
public class BillToAccountScheduled {
    private static final Logger LOG					= LoggerFactory.getLogger( BillToAccountScheduled.class );

    @Autowired
    private IwalletCaseService caseService;

    @Scheduled(cron = "0/10 * * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void fundAccount() {
        try {
            LOG.info("开始同步");
            caseService.BillToAccount();
            LOG.info("同步结束");
        } catch (Exception e) {
            LOG.error("同步错误：" + e.getMessage(),e);
            throw e;
        }
    }
}

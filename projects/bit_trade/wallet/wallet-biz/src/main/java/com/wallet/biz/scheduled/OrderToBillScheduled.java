package com.wallet.biz.scheduled;

import com.wallet.biz.api.service.IwalletCaseService;
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
public class OrderToBillScheduled {
    private static final Logger LOG					= LoggerFactory.getLogger( OrderToBillScheduled.class );

    @Autowired
    private IwalletCaseService caseService;

    @Scheduled(cron = "0/10 * * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void fundAccount() {
        try {
            LOG.info("开始上链");
            caseService.OrderToBill();
            LOG.info("上链结束");
        } catch (Exception e) {
            LOG.error("上链错误：" + e.getMessage(),e);
            throw e;
        }
    }
}

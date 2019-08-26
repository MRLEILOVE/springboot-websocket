package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.pojo.BetaFreezeAccountStat;

import java.util.Date;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-08-10
 */
public interface BetaFreezeAccountStatService extends IService<BetaFreezeAccountStat> {

    /**
     * @Description: 统计冻结总额
     * @Author: senghor
     * @Date: 2019/8/10 19:21
     */
    BetaFreezeAccountStat statisticsFreezeAccountStat(Date startDate, Date endDate);
}

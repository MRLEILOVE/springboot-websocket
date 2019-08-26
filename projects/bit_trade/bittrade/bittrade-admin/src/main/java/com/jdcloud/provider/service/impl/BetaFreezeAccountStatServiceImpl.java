package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.provider.mapper.BetaFreezeAccountStatMapper;
import com.jdcloud.provider.pojo.BetaFreezeAccountStat;
import com.jdcloud.provider.service.BetaFreezeAccountStatService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-08-10
 */
@Service
public class BetaFreezeAccountStatServiceImpl extends ServiceImpl<BetaFreezeAccountStatMapper, BetaFreezeAccountStat> implements BetaFreezeAccountStatService {

    /**
     * @Description: 统计冻结总额
     * @Author: senghor
     * @Date: 2019/8/10 19:21
     */
    @Override
    public BetaFreezeAccountStat statisticsFreezeAccountStat(Date startDate, Date endDate) {
        return baseMapper.statisticsFreezeAccountStat(startDate, endDate);
    }
}

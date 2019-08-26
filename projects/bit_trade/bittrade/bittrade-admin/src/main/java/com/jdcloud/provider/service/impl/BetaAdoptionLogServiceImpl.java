package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.provider.dto.BetaAdoptionLogDto;
import com.jdcloud.provider.mapper.BetaAdoptionLogMapper;
import com.jdcloud.provider.pojo.BetaAdoptionLog;
import com.jdcloud.provider.pojo.vo.BetaAdoptionLogVo;
import com.jdcloud.provider.service.BetaAccountRecordService;
import com.jdcloud.provider.service.BetaAdoptionLogService;
import com.jdcloud.provider.service.BetaOrderService;
import com.jdcloud.util.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-05-15
 */
@Service
public class BetaAdoptionLogServiceImpl extends ServiceImpl<BetaAdoptionLogMapper, BetaAdoptionLog> implements BetaAdoptionLogService {

    @Autowired
    private BetaOrderService betaOrderService;

    @Override
    public Page<BetaAdoptionLogVo> selectPageList(Page<BetaAdoptionLogVo> page, BetaAdoptionLogDto betaAdoptionLogDto) {
        List<BetaAdoptionLogVo> betaAdoptionLogVos = baseMapper.selectPageList(page, betaAdoptionLogDto);
        for (int i = 0; i < betaAdoptionLogVos.size(); i++) {
            BetaAdoptionLogVo betaAdoptionLogVo = betaAdoptionLogVos.get(i);
            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.ge("register_time", DateTimeUtils.getTimeSecond(betaAdoptionLogVo.getDay()));
            entityWrapper.le("register_time", DateTimeUtils.getEndTime(betaAdoptionLogVo.getDay()));
            entityWrapper.eq("combo_id", betaAdoptionLogVo.getComboGroupId());
            entityWrapper.eq("user_id", betaAdoptionLogVo.getUserId());
            Integer isGetBeta = betaOrderService.selectCount(entityWrapper);
            if (isGetBeta > 0) {
                betaAdoptionLogVo.setIsGetBeta("是");
            } else {
                betaAdoptionLogVo.setIsGetBeta("否");
            }
            betaAdoptionLogVos.set(i, betaAdoptionLogVo);
        }
        page.setRecords(betaAdoptionLogVos);
        return page;
    }
}

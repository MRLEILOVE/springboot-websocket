package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.BetaReservationDto;
import com.jdcloud.provider.pojo.BetaReservation;
import com.jdcloud.provider.pojo.vo.BetaReservationVo;
import com.jdcloud.util.wrapper.Wrapper;

/**
 * <p>
 * 贝塔狗---预约表 服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-04-24
 */
public interface BetaReservationService extends IService<BetaReservation> {

    Page<BetaReservationVo> selectBetaReservationListPage(Page<BetaReservationVo> page, BetaReservationDto dto);

    BetaReservationVo selectBetaReservation(Integer id);

    Wrapper<String> saveOrUpdate(BetaReservation beta);

    int deleteByIds(String ids);

    /**
     * @Description: 刷新缓存中的预约数据
     * @Author: senghor
     * @Date: 2019/6/8 13:30
     */
    Wrapper<String> refreshRedis();
}

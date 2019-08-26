package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.base.constant.GlobalConstant;
import com.jdcloud.core.redis.RedisKeyUtil;
import com.jdcloud.core.redis.RedisTemplate;
import com.jdcloud.provider.dto.BetaReservationDto;
import com.jdcloud.provider.mapper.BetaReservationMapper;
import com.jdcloud.provider.pojo.BetaReservation;
import com.jdcloud.provider.pojo.ComboGroup;
import com.jdcloud.provider.pojo.PersonalCard;
import com.jdcloud.provider.pojo.vo.BetaReservationVo;
import com.jdcloud.provider.service.BetaReservationService;
import com.jdcloud.provider.service.PersonalCardService;
import com.jdcloud.util.Convert;
import com.jdcloud.util.date.DateTimeUtils;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.search.SearchTerm;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 贝塔狗---预约表 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-04-24
 */
@Service
public class BetaReservationServiceImpl extends ServiceImpl<BetaReservationMapper, BetaReservation> implements BetaReservationService {

    @Resource
    private BetaReservationMapper betaReservationMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private PersonalCardService personalCardService;

    @Override
    public Page<BetaReservationVo> selectBetaReservationListPage(Page<BetaReservationVo> page, BetaReservationDto dto) {
        page.setRecords(betaReservationMapper.selectBetaReservationListPage(page,dto));
        return page;
    }

    @Override
    public BetaReservationVo selectBetaReservation(Integer id) {
        return betaReservationMapper.selectBetaReservation(id);
    }

    @Override
    public Wrapper<String> saveOrUpdate(BetaReservation beta) {
        BetaReservation be = new BetaReservation();
        if (beta.getId() == null) {
            betaReservationMapper.insert(beta);
        } else {
            BeanUtils.copyProperties(beta, be); //参数拷贝到be中
            be.setUpdateTime(new Date());
            betaReservationMapper.updateById(be);
        }
        return WrapMapper.ok("修改成功");
    }

    @Override
    public int deleteByIds(String ids) {
        Integer[] Ids = ConvertUtil.toIntArray(ids);
        for (int i = 0; i < Ids.length; i++) {
            BetaReservation betaReservation = baseMapper.selectById(Ids[i]);
            if (betaReservation != null) {
                redisTemplate.delete(RedisKeyUtil.BETA_RESERVATION + betaReservation.getUserId() + ":"
                        + betaReservation.getComboId());
            }
        }
        int row = betaReservationMapper.deleteByIds(Ids);
        return row;
    }

    /**
     * @Description: 刷新缓存数据
     * @Author: senghor
     * @Date: 2019/6/8 13:30
     */
    @Override
    public Wrapper<String> refreshRedis() {
        // 清空之前的缓存
        redisTemplate.delete(redisTemplate.keys(RedisKeyUtil.BETA_RESERVATION + "*"));

        // 获取今日有预约的实名用户
        List<BetaReservation> reservations = baseMapper.selectTodayList(DateTimeUtils.getDays());
        for (BetaReservation betaReservation : reservations) {
            redisTemplate.set(RedisKeyUtil.BETA_RESERVATION + betaReservation.getUserId() + ":"
                    + betaReservation.getComboId(), 1, GlobalConstant.Number.DAY_TIME_LONG);
        }
        return WrapMapper.ok("刷新成功");
    }
}

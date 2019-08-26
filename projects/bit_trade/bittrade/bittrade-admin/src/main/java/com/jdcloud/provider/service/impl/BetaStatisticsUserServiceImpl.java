package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.BetaStatisticsUserDto;
import com.jdcloud.provider.pojo.BetaStatisticsUser;
import com.jdcloud.provider.mapper.BetaStatisticsUserMapper;
import com.jdcloud.provider.pojo.vo.RegisterEChartsVo;
import com.jdcloud.provider.service.BetaStatisticsUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.provider.web.base.BaseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 统计用户注册人数表 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-07-06
 */
@Service
public class BetaStatisticsUserServiceImpl extends ServiceImpl<BetaStatisticsUserMapper, BetaStatisticsUser> implements BetaStatisticsUserService {


    @Override
    public List<BetaStatisticsUser> registeredcodeList(RegisterEChartsVo vo) {
        return baseMapper.registeredcodeList(vo);
    }

    /**
     * 统计列表
     * @param page
     * @param dto
     * @return
     */
    @Override
    public Page<BetaStatisticsUser> getRegistereList(Page<BetaStatisticsUser> page, BetaStatisticsUserDto dto) {
        return page.setRecords(baseMapper.getRegistereList(page,dto));
    }

    @Override
    public int getSumHouseNum(Integer type) {
        return baseMapper.getSumHouseNum(type);
    }
}

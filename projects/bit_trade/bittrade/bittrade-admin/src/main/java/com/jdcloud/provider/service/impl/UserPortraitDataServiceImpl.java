package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.provider.mapper.UserPortraitDataMapper;
import com.jdcloud.provider.pojo.UserPortraitData;
import com.jdcloud.provider.pojo.vo.PortraitDataVo;
import com.jdcloud.provider.service.UserPortraitDataService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 用户人群画像服务实现
 * <br/>
 *
 * @author ：leigq
 * @date ：2019/8/12 10:22
 */
@Service
public class UserPortraitDataServiceImpl extends ServiceImpl<UserPortraitDataMapper, UserPortraitData> implements UserPortraitDataService {

    @Override
    public List<PortraitDataVo> listAreaData(Long startTime, Long endTime, Integer groupType) {
        return baseMapper.listAreaData(startTime, endTime, groupType);
    }

    @Override
    public PortraitDataVo listAgeData(Long startTime, Long endTime, int startAge, int endAge) {
        return baseMapper.listAgeData(startTime, endTime, startAge, endAge);
    }

    @Override
    public List<PortraitDataVo> listSexData(Long startTime, Long endTime) {
        return baseMapper.listSexData(startTime, endTime);
    }

	@Override
	public List<PortraitDataVo> listConstellationData(Long startTime, Long endTime) {
		return baseMapper.listConstellationData(startTime, endTime);
	}

	@Override
	public List<PortraitDataVo> listZodiacData(Long startTime, Long endTime) {
		return baseMapper.listZodiacData(startTime, endTime);
	}
}

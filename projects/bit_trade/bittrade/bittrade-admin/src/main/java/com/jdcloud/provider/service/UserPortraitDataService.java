package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.pojo.UserPortraitData;
import com.jdcloud.provider.pojo.vo.PortraitDataVo;

import java.util.List;

/**
 * 用户人群画像服务
 * <br/>
 * @author     ：leigq
 * @date       ：2019/8/12 10:22
 */
public interface UserPortraitDataService extends IService<UserPortraitData> {

    /**
     * @Description: 根据分组类型获取数据
     * @param groupType :
     * @Author: zjun
     * @Date: 2019/8/12 17:28
     */
    List<PortraitDataVo> listAreaData(Long startTime, Long endTime, Integer groupType);

    PortraitDataVo listAgeData(Long startTime, Long endTime, int startAge, int endAge);

    List<PortraitDataVo> listSexData(Long startTime, Long endTime);

    List<PortraitDataVo> listConstellationData(Long startTime, Long endTime);

    List<PortraitDataVo> listZodiacData(Long startTime, Long endTime);
}

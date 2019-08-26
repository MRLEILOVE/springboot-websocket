package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.BetaStatisticsUserDto;
import com.jdcloud.provider.pojo.BetaStatisticsUser;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.pojo.vo.RegisterEChartsVo;
import com.jdcloud.provider.web.base.BaseEntity;

import java.util.List;

/**
 * <p>
 * 统计用户注册人数表 服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-07-06
 */
public interface BetaStatisticsUserService extends IService<BetaStatisticsUser> {

    List<BetaStatisticsUser> registeredcodeList(RegisterEChartsVo vo);

    Page<BetaStatisticsUser> getRegistereList(Page<BetaStatisticsUser> page, BetaStatisticsUserDto dto);

    /**
     * @Description: 获取总狗窝数
     * @param type :
     * @Author: zjun
     * @Date: 2019/8/7 15:16
     */
    int getSumHouseNum(Integer type);
}

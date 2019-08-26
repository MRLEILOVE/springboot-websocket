package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.HouseBackpackUseDto;
import com.jdcloud.provider.pojo.HouseBackpackUse;
import com.jdcloud.provider.pojo.vo.HouseBackpackUseVo;

import java.util.List;

/**
 * <p>
 * 背包表 服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-06-21
 */
public interface HouseBackpackUseService extends IService<HouseBackpackUse> {

    Page<HouseBackpackUseVo> getBackpackUseList(Page<HouseBackpackUseVo> page, HouseBackpackUseDto houseBackpackUseDto);

    /**
     * @Description: 获取多个使用记录
     * @Author: zjun
     * @Date: 2019/6/17 16:28
     */
    List<HouseBackpackUseVo> selectBackpackUseList(Long houseId, String type, Integer beSuitable, Long puppyId);
}

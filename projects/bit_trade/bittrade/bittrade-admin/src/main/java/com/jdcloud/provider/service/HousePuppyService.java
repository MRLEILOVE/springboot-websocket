package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.HousePuppyDto;
import com.jdcloud.provider.pojo.HousePuppy;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.pojo.vo.HouseProductVo;
import com.jdcloud.provider.pojo.vo.HousePuppyVo;

/**
 * <p>
 * 小狗表 服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-06-13
 */
public interface HousePuppyService extends IService<HousePuppy> {
    /**
     * 小狗列表
     * @param page
     * @param housePuppyDto
     * @return
     */
    Page<HousePuppyVo> getHousePuppy(Page<HousePuppyVo> page, HousePuppyDto housePuppyDto);
}

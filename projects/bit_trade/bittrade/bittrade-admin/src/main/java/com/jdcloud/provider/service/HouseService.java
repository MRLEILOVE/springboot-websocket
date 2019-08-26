package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.HouseDto;
import com.jdcloud.provider.pojo.House;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.pojo.vo.HouseVo;

/**
 * <p>
 * 狗窝表 服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-06-13
 */
public interface HouseService extends IService<House> {

    Page<HouseVo> getHouse(Page<HouseVo> page, HouseDto houseDto);

    HouseVo getDetail(Integer id);
}

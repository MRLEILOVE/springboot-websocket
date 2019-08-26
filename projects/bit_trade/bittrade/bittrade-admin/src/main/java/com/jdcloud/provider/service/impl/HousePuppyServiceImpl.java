package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.HousePuppyDto;
import com.jdcloud.provider.pojo.HousePuppy;
import com.jdcloud.provider.mapper.HousePuppyMapper;
import com.jdcloud.provider.pojo.vo.HouseProductVo;
import com.jdcloud.provider.pojo.vo.HousePuppyVo;
import com.jdcloud.provider.service.HousePuppyService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 小狗表 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-06-13
 */
@Service
public class HousePuppyServiceImpl extends ServiceImpl<HousePuppyMapper, HousePuppy> implements HousePuppyService {

    /**
     * 小狗列表
     * @param page
     * @param housePuppyDto
     * @return
     */
    @Override
    public Page<HousePuppyVo> getHousePuppy(Page<HousePuppyVo> page, HousePuppyDto housePuppyDto) {
        page.setRecords(baseMapper.getHousePuppy(page,housePuppyDto));
        return page;
    }
}

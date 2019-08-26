package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.provider.dto.HouseOrderDto;
import com.jdcloud.provider.mapper.HouseOrderMapper;
import com.jdcloud.provider.pojo.HouseOrder;
import com.jdcloud.provider.pojo.vo.HouseOrderVo;
import com.jdcloud.provider.service.HouseOrderService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-06-19
 */
@Service
public class HouseOrderServiceImpl extends ServiceImpl<HouseOrderMapper, HouseOrder> implements HouseOrderService {

    /**
     * 列表
     * @param page
     * @param houseOrderDto
     * @return
     */
    @Override
    public Page<HouseOrderVo> getHouseOrder(Page<HouseOrderVo> page, HouseOrderDto houseOrderDto) {
        page.setRecords(baseMapper.getHouseOrder(page,houseOrderDto));
        return page;
    }
}

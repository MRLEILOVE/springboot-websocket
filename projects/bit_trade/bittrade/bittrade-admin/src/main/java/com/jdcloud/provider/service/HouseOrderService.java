package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.HouseOrderDto;
import com.jdcloud.provider.pojo.HouseOrder;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.pojo.vo.HouseOrderVo;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-06-19
 */
public interface HouseOrderService extends IService<HouseOrder> {

    Page<HouseOrderVo> getHouseOrder(Page<HouseOrderVo> page, HouseOrderDto houseOrderDto);
}

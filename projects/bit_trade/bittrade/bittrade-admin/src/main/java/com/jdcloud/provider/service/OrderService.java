package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.OrderDto;
import com.jdcloud.provider.pojo.Order;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.pojo.vo.OrderVo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-07-16
 */
public interface OrderService extends IService<Order> {

    Page<OrderVo> orderList(Page<OrderVo> page,OrderDto orderDto);

    BigDecimal getOrderCount(Date startDate,Date endDate,int type,String token);

    BigDecimal getOrderCoinCount(Date startDate,Date endDate,int type,String token);

    // 统计充币数量
    int getOrderCoinCountSucceed(Date startDate,Date endDate,int type,String token);
}

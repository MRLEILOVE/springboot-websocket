package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.OrderDto;
import com.jdcloud.provider.pojo.Order;
import com.jdcloud.provider.mapper.OrderMapper;
import com.jdcloud.provider.pojo.vo.OrderVo;
import com.jdcloud.provider.service.OrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-07-16
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    /**
     * 订单列表
     * @param page
     * @param orderDto
     * @return
     */
    @Override
    public Page<OrderVo> orderList(Page<OrderVo> page, OrderDto orderDto){
        page.setRecords(baseMapper.orderList(page,orderDto));
        return page;
    }


    /**
     * 统计币种_今日提币总数
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @param type      充币类型、
     * @param token     币种
     * @return           提币总数
     */
    @Override
    public BigDecimal getOrderCount(Date startDate, Date endDate, int type,String token) {
        BigDecimal  count  = baseMapper.getOrderCount(startDate,endDate,type,token);
        return count;
    }

    /**
     * 统计币种_今日充币总数
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @param type      充币类型
     * @param token     币种
     * @return 充币总数
     */
    @Override
    public BigDecimal getOrderCoinCount(Date startDate, Date endDate, int type,String token) {
        BigDecimal  count  = baseMapper.getOrderCoinCount(startDate,endDate,type,token);
        return count;
    }

    /**
     * 统计币种_充币成功数
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @param type      充币类型
     * @return          成功笔数
     */
    @Override
    public int getOrderCoinCountSucceed(Date startDate, Date endDate, int type,String token) {
        int  count  = baseMapper.getOrderCoinCountSucceed(startDate,endDate,type,token);
        return count;
    }
}

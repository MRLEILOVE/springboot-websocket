package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.provider.dto.OrderEntrustPageDto;
import com.jdcloud.provider.mapper.OrderEntrustMapper;
import com.jdcloud.provider.pojo.OrderEntrust;
import com.jdcloud.provider.pojo.vo.OrderEntrustVo;
import com.jdcloud.provider.service.OrderEntrustService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 委托订单表 服务实现类
 * </p>
 *
 * @author helen
 * @since 2019-03-03
 */
@Service
public class OrderEntrustServiceImpl extends ServiceImpl<OrderEntrustMapper, OrderEntrust> implements OrderEntrustService {

    @Resource
    private OrderEntrustMapper orderEntrustMapper;

    @Override
    public Page<OrderEntrustVo> orderEntrustServiceList(Page<OrderEntrustVo> page, OrderEntrustPageDto orderEntrustPageDto){
        List<OrderEntrustVo> orderEntrustPageVo= orderEntrustMapper.selectOrderEntrustList(page,orderEntrustPageDto);
        page.setRecords(orderEntrustPageVo);
        return page;
    }

    @Override
    public List<OrderEntrustVo> selectExportList(OrderEntrustPageDto orderEntrustPageDto){
        List<OrderEntrustVo> orderEntrustPageVo= orderEntrustMapper.selectExportList(orderEntrustPageDto);
        return orderEntrustPageVo;
    }
}

package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.OrderEntrustPageDto;
import com.jdcloud.provider.pojo.OrderEntrust;
import com.jdcloud.provider.pojo.vo.OrderEntrustVo;

import java.util.List;

/**
 * <p>
 * 委托订单表 服务类
 * </p>
 *
 * @author helen
 * @since 2019-03-03
 */
public interface OrderEntrustService extends IService<OrderEntrust> {

    /**
     * c查询委托单
     * @return
     */
    Page<OrderEntrustVo> orderEntrustServiceList(Page<OrderEntrustVo> page, OrderEntrustPageDto orderEntrustPageDto);

    /**
     * @Description: 查询导出列表
     * @Author: senghor
     * @Date: 2019/5/13 0013 20:58
     */

    List<OrderEntrustVo> selectExportList(OrderEntrustPageDto orderEntrustPageDto);
}

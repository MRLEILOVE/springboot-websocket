package com.jdcloud.provider.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.TOrderDto;
import com.jdcloud.provider.dto.TotalDto;
import com.jdcloud.provider.pojo.TOrder;
import com.jdcloud.provider.pojo.vo.TOrderVo;
import com.jdcloud.provider.pojo.vo.TotalVo;
import com.jdcloud.util.wrapper.Wrapper;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2019-07-08
 */

public interface ITOrderService extends IService<TOrder> {

    Wrapper<String> refuse(Integer id);
    Page<TOrderVo> getCustomerList(Page<TOrderVo> page, TOrderDto tOrderDto);

    Page<TOrderVo> getFinanceList(Page<TOrderVo> page, TOrderDto tOrderDto);

    Page<TOrderVo> getCustomerPage(Page<TOrderVo> page, TOrderDto tOrderDto);

    Wrapper financialRejection(Integer id);

    Page<TotalVo> getTotal(Page<TotalVo> page,TotalDto dto);

    TOrderVo getCustomerDetail(Integer id);





  /*  Page<TOrder> selectCustomerList();

    Page<TOrder> selectFinanceList(TOrderDto tOrderDto);*/
}

package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.BetaProcessedaOrderDto;
import com.jdcloud.provider.pojo.BetaProcessedaOrder;
import com.jdcloud.provider.pojo.vo.BetaProcessedaOrderVo;
import com.jdcloud.util.wrapper.Wrapper;

/**
 * <p>
 * beta订单处理表  服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-04-24
 */
public interface BetaProcessedaOrderService extends IService<BetaProcessedaOrder> {

    Page<BetaProcessedaOrderVo> betaProcessedaOrderListPage(Page<BetaProcessedaOrderVo> page, BetaProcessedaOrderDto betaProcessedaOrderDto);

    BetaProcessedaOrderVo betaProcessedaOrderDetails(Integer id);

    int betaProcessedaOrderdeletes(String ids);

    Wrapper updateRefuseDogoRecall(Integer id);

    Wrapper updateputTheDog(Integer id);
}

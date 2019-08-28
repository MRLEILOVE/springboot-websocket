package com.wallet.biz.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bittrade.pojo.model.WOrder;
import com.core.common.DTO.ReturnDTO;

/**
 * 
 * @author Administrator
 *
 */
public interface IWOrderService extends IService<WOrder> {
    ReturnDTO addOrder(WOrder order );
}

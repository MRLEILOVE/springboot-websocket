package com.wallet.biz.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.core.common.DTO.ReturnDTO;
import com.wallet.biz.pojo.model.WOrder;

/**
 * 
 * @author Administrator
 *
 */
public interface IWOrderService extends IService<WOrder> {
    ReturnDTO addOrder(WOrder order );
}

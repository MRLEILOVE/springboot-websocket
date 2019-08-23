package com.wallet.biz.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.core.common.DTO.ReturnDTO;
import com.wallet.biz.pojo.model.WOrder;
import com.wallet.biz.pojo.vo.AddressParamDto;
import com.wallet.biz.pojo.vo.OrderVO;

/**
 * 
 * @author Administrator
 *
 */
public interface IWOrderService extends IService<WOrder> {
    ReturnDTO addOrder(WOrder order );
}

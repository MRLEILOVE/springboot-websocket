package com.walletbiz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.core.common.DTO.ReturnDTO;
import com.walletbiz.pojo.TOrder;
import com.walletbiz.vo.WithDrawParamVo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jobob
 * @since 2019-07-08
 */
public interface ITOrderService extends IService<TOrder> {

	ReturnDTO<String> confirmTibi(WithDrawParamVo withDrawParamVo);
}

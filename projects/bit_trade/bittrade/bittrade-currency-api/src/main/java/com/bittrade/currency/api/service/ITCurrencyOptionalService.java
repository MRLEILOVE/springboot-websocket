package com.bittrade.currency.api.service;

import java.util.List;

import com.bittrade.__default.service.IDefaultTCurrencyOptionalService;
import com.bittrade.pojo.dto.TCurrencyOptionalDTO;
import com.bittrade.pojo.model.TCurrencyOptional;
import com.bittrade.pojo.vo.TCurrencyOptionalVO;
import com.bittrade.pojo.vo.TransactionPairVO;
import com.core.framework.DTO.ReturnDTO;

/**
 * 
 * @author Administrator
 *
 */
public interface ITCurrencyOptionalService extends IDefaultTCurrencyOptionalService<TCurrencyOptional, TCurrencyOptionalDTO, TCurrencyOptionalVO> {
    /**
     * 查询用户自选的交易对
     */
    List<TransactionPairVO> findOptionalByUserId(String userId);

    /**
     * 添加自选
     */
    ReturnDTO<String> addOptional(TCurrencyOptionalDTO currencyOptionalDTO);

    /**
     * 删除自选
     */
    ReturnDTO<String> deleteOptional(TCurrencyOptionalDTO currencyOptionalDTO);
}

package com.bittrade.api.service;

import com.bittrade.api.__default.service.IDefaultTCurrencyOptionalService;
import com.bittrade.api.__default.DAO.IDefaultTCurrencyOptionalDAO;
import com.bittrade.pojo.dto.TCurrencyOptionalDTO;
import com.bittrade.pojo.vo.TCurrencyOptionalVO;
import com.bittrade.pojo.model.TCurrencyOptional;
import com.bittrade.pojo.vo.TransactionPairVO;
import com.core.framework.DTO.ReturnDTO;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public interface ITCurrencyOptionalService extends IDefaultTCurrencyOptionalService<TCurrencyOptional, TCurrencyOptionalDTO, TCurrencyOptionalVO, IDefaultTCurrencyOptionalDAO> {
    /**
     * 查询用户自选的交易对
     */
    List<TransactionPairVO> findOptionalByUserId(String userId);

    /**
     * 添加自选
     */
    ReturnDTO addOptional(TCurrencyOptionalDTO currencyOptionalDTO);

    /**
     * 删除自选
     */
    ReturnDTO deleteOptional(TCurrencyOptionalDTO currencyOptionalDTO);
}

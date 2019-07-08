package com.bittrade.api.service;

import java.util.List;

import com.bittrade.api.__default.service.IDefaultTCurrencyOptionalService;
import com.bittrade.pojo.dto.TCurrencyOptionalDTO;
import com.bittrade.pojo.model.TCurrencyOptional;
import com.bittrade.pojo.vo.TCurrencyOptionalVO;
import com.bittrade.pojo.vo.TransactionPairVO;
import com.core.framework.DTO.ReturnDTO;
import com.core.framework.base.DAO.IBaseDAO;

/**
 * 
 * @author Administrator
 *
 */
public interface ITCurrencyOptionalService<DAO extends IBaseDAO<TCurrencyOptional, TCurrencyOptionalDTO, TCurrencyOptionalVO>> extends IDefaultTCurrencyOptionalService<TCurrencyOptional, TCurrencyOptionalDTO, TCurrencyOptionalVO, DAO> {
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

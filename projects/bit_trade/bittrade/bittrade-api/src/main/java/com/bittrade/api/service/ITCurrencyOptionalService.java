package com.bittrade.api.service;

import java.util.List;

import com.bittrade.api.__default.service.IDefaultTCurrencyOptionalService;
import com.bittrade.pojo.dto.TCurrencyOptionalDTO;
import com.bittrade.pojo.vo.TransactionPairVO;
import com.core.framework.DTO.ReturnDTO;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.DTO.BaseDTO;
import com.core.framework.base.VO.BaseVO;
import com.core.framework.base.model.BaseModel;

/**
 * 
 * @author Administrator
 *
 */
public interface ITCurrencyOptionalService<Model extends BaseModel<Model>, DTO extends BaseDTO<DTO>, VO extends BaseVO<VO>, DAO extends IBaseDAO<Model, DTO, VO>> extends IDefaultTCurrencyOptionalService<Model, DTO, VO, DAO> {
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

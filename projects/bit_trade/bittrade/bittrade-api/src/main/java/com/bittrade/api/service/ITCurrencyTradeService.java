package com.bittrade.api.service;

import java.util.List;

import com.bittrade.api.__default.service.IDefaultTCurrencyTradeService;
import com.bittrade.pojo.vo.TransactionPairVO;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.DTO.BaseDTO;
import com.core.framework.base.VO.BaseVO;
import com.core.framework.base.model.BaseModel;

/**
 * 
 * @author Administrator
 *
 */
public interface ITCurrencyTradeService<Model extends BaseModel<Model>, DTO extends BaseDTO<DTO>, VO extends BaseVO<VO>, DAO extends IBaseDAO<Model, DTO, VO>> extends IDefaultTCurrencyTradeService<Model, DTO, VO, DAO> {
    /**
     * 根据法币id查找交易对
     */
    List<TransactionPairVO> findTradeByCurrencyId(String currencyId2);
}

package com.bittrade.api.service;

import java.util.List;

import com.bittrade.api.__default.service.IDefaultTCurrencyService;
import com.bittrade.pojo.vo.TCurrencyVO;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.DTO.BaseDTO;
import com.core.framework.base.VO.BaseVO;
import com.core.framework.base.model.BaseModel;

/**
 * 
 * @author Administrator
 *
 */
public interface ITCurrencyService<Model extends BaseModel<Model>, DTO extends BaseDTO<DTO>, VO extends BaseVO<VO>, DAO extends IBaseDAO<Model, DTO, VO>> extends IDefaultTCurrencyService<Model, DTO, VO, DAO> {
    /**
     * 查找所有法币
     */
    List<TCurrencyVO> findAllLegalCurrency();
}

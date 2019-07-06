/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.api.__default.service.impl;

import com.bittrade.api.__default.service.IDefaultTTickerService;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.DTO.BaseDTO;
import com.core.framework.base.VO.BaseVO;
import com.core.framework.base.model.BaseModel;
import com.core.framework.base.service.impl.BaseServiceImpl;

/**
 * 
 * @author Administrator
 *
 */
//public abstract class DefaultTTickerServiceImpl extends BaseServiceImpl<TTicker, TTickerDTO, TTickerVO, ITTickerDAO> implements IDefaultTTickerService {
public abstract class DefaultTTickerServiceImpl<DAO extends IBaseDAO<Model, DTO, VO>, Model extends BaseModel<Model>, DTO extends BaseDTO<DTO>, VO extends BaseVO<VO>> extends BaseServiceImpl<Model, DTO, VO, DAO> implements IDefaultTTickerService<Model, DTO, VO, DAO> {
	
}

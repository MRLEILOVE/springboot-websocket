/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.api.__default.service.impl;

import com.bittrade.api.__default.service.IDefaultTKlineService;
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
//public abstract class DefaultTKlineServiceImpl extends BaseServiceImpl<TKline, TKlineDTO, TKlineVO, ITKlineDAO> implements IDefaultTKlineService {
public abstract class DefaultTKlineServiceImpl<DAO extends IBaseDAO<Model, DTO, VO>, Model extends BaseModel<Model>, DTO extends BaseDTO<DTO>, VO extends BaseVO<VO>> extends BaseServiceImpl<Model, DTO, VO, DAO> implements IDefaultTKlineService<Model, DTO, VO, DAO> {
	
}

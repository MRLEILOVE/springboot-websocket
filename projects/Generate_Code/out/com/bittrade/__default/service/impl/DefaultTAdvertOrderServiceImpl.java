/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.service.impl;

import com.bittrade.__default.service.IDefaultTAdvertOrderService;
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
//public abstract class DefaultTAdvertOrderServiceImpl extends BaseServiceImpl<TAdvertOrder, TAdvertOrderDTO, TAdvertOrderVO, ITAdvertOrderDAO> implements IDefaultTAdvertOrderService {
public abstract class DefaultTAdvertOrderServiceImpl<DAO extends IBaseDAO<Model, DTO, VO>, Model extends BaseModel<Model>, DTO extends BaseDTO<DTO>, VO extends BaseVO<VO>> extends BaseServiceImpl<Model, DTO, VO, DAO> implements IDefaultTAdvertOrderService<Model, DTO, VO> {
	
}

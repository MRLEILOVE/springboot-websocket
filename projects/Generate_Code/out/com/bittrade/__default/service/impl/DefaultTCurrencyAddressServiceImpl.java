/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.service.impl;

import com.bittrade.__default.service.IDefaultTCurrencyAddressService;
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
//public abstract class DefaultTCurrencyAddressServiceImpl extends BaseServiceImpl<TCurrencyAddress, TCurrencyAddressDTO, TCurrencyAddressVO, ITCurrencyAddressDAO> implements IDefaultTCurrencyAddressService {
public abstract class DefaultTCurrencyAddressServiceImpl<DAO extends IBaseDAO<Model, DTO, VO>, Model extends BaseModel<Model>, DTO extends BaseDTO<DTO>, VO extends BaseVO<VO>> extends BaseServiceImpl<Model, DTO, VO, DAO> implements IDefaultTCurrencyAddressService<Model, DTO, VO> {
	
}
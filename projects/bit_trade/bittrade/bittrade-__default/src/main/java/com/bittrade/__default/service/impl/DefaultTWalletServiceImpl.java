/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.service.impl;

import com.bittrade.__default.service.IDefaultTWalletService;
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
//public abstract class DefaultTWalletServiceImpl extends BaseServiceImpl<TWallet, TWalletDTO, TWalletVO, ITWalletDAO> implements IDefaultTWalletService {
public abstract class DefaultTWalletServiceImpl<DAO extends IBaseDAO<Model, DTO, VO>, Model extends BaseModel<Model>, DTO extends BaseDTO<DTO>, VO extends BaseVO<VO>> extends BaseServiceImpl<Model, DTO, VO, DAO> implements IDefaultTWalletService<Model, DTO, VO> {
	
}

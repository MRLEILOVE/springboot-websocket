/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.service.impl;

import com.bittrade.pojo.model.SysDept;
import com.bittrade.pojo.dto.SysDeptDTO;
import com.bittrade.pojo.vo.SysDeptVO;
import com.bittrade.__default.service.IDefaultSysDeptService;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.service.impl.BaseServiceImpl;

/**
 * 
 * @author Administrator
 *
 */
//public abstract class DefaultSysDeptServiceImpl extends BaseServiceImpl<SysDept, SysDeptDTO, SysDeptVO, ISysDeptDAO> implements IDefaultSysDeptService {
public abstract class DefaultSysDeptServiceImpl<DAO extends IBaseDAO<SysDept, SysDeptDTO, SysDeptVO>> extends BaseServiceImpl<SysDept, SysDeptDTO, SysDeptVO, DAO> implements IDefaultSysDeptService {
	
}

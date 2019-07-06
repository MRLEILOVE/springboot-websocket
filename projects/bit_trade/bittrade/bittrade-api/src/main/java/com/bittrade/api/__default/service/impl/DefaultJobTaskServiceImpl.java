/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.api.__default.service.impl;

import com.bittrade.api.__default.service.IDefaultJobTaskService;
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
//public abstract class DefaultJobTaskServiceImpl extends BaseServiceImpl<JobTask, JobTaskDTO, JobTaskVO, IJobTaskDAO> implements IDefaultJobTaskService {
public abstract class DefaultJobTaskServiceImpl<DAO extends IBaseDAO<Model, DTO, VO>, Model extends BaseModel<Model>, DTO extends BaseDTO<DTO>, VO extends BaseVO<VO>> extends BaseServiceImpl<Model, DTO, VO, DAO> implements IDefaultJobTaskService<Model, DTO, VO, DAO> {
	
}

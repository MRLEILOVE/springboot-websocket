package com.bittrade.api.service;

import com.core.framework.base.DAO.IBaseDAO;

import com.bittrade.api.__default.service.IDefaultJobTaskService;
import com.bittrade.pojo.dto.JobTaskDTO;
import com.bittrade.pojo.vo.JobTaskVO;
import com.bittrade.pojo.model.JobTask;

/**
 * 
 * @author Administrator
 *
 */
public interface IJobTaskService<DAO extends IBaseDAO<JobTask, JobTaskDTO, JobTaskVO>> extends IDefaultJobTaskService<JobTask, JobTaskDTO, JobTaskVO, DAO> {
	
}

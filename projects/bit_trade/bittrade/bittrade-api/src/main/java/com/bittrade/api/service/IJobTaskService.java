package com.bittrade.api.service;

import com.bittrade.api.__default.service.IDefaultJobTaskService;
import com.bittrade.api.__default.DAO.IDefaultJobTaskDAO;
import com.bittrade.pojo.dto.JobTaskDTO;
import com.bittrade.pojo.vo.JobTaskVO;
import com.bittrade.pojo.model.JobTask;

/**
 * 
 * @author Administrator
 *
 */
public interface IJobTaskService extends IDefaultJobTaskService<JobTask, JobTaskDTO, JobTaskVO, IDefaultJobTaskDAO> {
	
}

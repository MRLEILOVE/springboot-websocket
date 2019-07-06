package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.api.__default.DAO.IDefaultJobTaskDAO;
import com.bittrade.api.__default.service.impl.DefaultJobTaskServiceImpl;
import com.bittrade.api.service.IJobTaskService;
import com.bittrade.pojo.dto.JobTaskDTO;
import com.bittrade.pojo.model.JobTask;
import com.bittrade.pojo.vo.JobTaskVO;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class JobTaskServiceImpl extends DefaultJobTaskServiceImpl<IDefaultJobTaskDAO, JobTask, JobTaskDTO, JobTaskVO> implements IJobTaskService {
	
}

package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.api.__default.service.impl.DefaultJobTaskServiceImpl;
import com.bittrade.api.dao.IJobTaskDAO;
import com.bittrade.pojo.dto.JobTaskDTO;
import com.bittrade.pojo.vo.JobTaskVO;
import com.bittrade.pojo.model.JobTask;
import com.bittrade.api.service.IJobTaskService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class JobTaskServiceImpl extends DefaultJobTaskServiceImpl<IJobTaskDAO, JobTask, JobTaskDTO, JobTaskVO> implements IJobTaskService {
	
}

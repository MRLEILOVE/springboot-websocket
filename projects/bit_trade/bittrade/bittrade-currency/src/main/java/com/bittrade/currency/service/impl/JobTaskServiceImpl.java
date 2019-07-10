package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultJobTaskServiceImpl;
import com.bittrade.currency.api.service.IJobTaskService;
import com.bittrade.currency.dao.IJobTaskDAO;
import com.bittrade.pojo.dto.JobTaskDTO;
import com.bittrade.pojo.model.JobTask;
import com.bittrade.pojo.vo.JobTaskVO;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class JobTaskServiceImpl extends DefaultJobTaskServiceImpl<IJobTaskDAO, JobTask, JobTaskDTO, JobTaskVO> implements IJobTaskService {
	
}

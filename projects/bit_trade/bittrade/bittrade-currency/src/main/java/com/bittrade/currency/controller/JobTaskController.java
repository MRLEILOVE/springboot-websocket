package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.currency.api.service.IJobTaskService;
import com.bittrade.pojo.dto.JobTaskDTO;
import com.bittrade.pojo.model.JobTask;
import com.bittrade.pojo.vo.JobTaskVO;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/jobTask" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class JobTaskController extends BaseController<JobTask, JobTaskDTO, JobTaskVO, IJobTaskService> {
	
}

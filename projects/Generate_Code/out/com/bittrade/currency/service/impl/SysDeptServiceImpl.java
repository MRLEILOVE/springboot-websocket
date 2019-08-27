package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultSysDeptServiceImpl;
import com.bittrade.currency.dao.ISysDeptDAO;
import com.bittrade.pojo.dto.SysDeptDTO;
import com.bittrade.pojo.vo.SysDeptVO;
import com.bittrade.pojo.model.SysDept;
import com.bittrade.currency.api.service.ISysDeptService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class SysDeptServiceImpl extends DefaultSysDeptServiceImpl<ISysDeptDAO, SysDept, SysDeptDTO, SysDeptVO> implements ISysDeptService {
	
}

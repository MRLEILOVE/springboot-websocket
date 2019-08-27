package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultSysDictTypeServiceImpl;
import com.bittrade.currency.dao.ISysDictTypeDAO;
import com.bittrade.pojo.dto.SysDictTypeDTO;
import com.bittrade.pojo.vo.SysDictTypeVO;
import com.bittrade.pojo.model.SysDictType;
import com.bittrade.currency.api.service.ISysDictTypeService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class SysDictTypeServiceImpl extends DefaultSysDictTypeServiceImpl<ISysDictTypeDAO, SysDictType, SysDictTypeDTO, SysDictTypeVO> implements ISysDictTypeService {
	
}

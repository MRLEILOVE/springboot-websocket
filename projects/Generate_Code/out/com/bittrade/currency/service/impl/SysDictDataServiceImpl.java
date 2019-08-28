package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultSysDictDataServiceImpl;
import com.bittrade.currency.dao.ISysDictDataDAO;
import com.bittrade.pojo.dto.SysDictDataDTO;
import com.bittrade.pojo.vo.SysDictDataVO;
import com.bittrade.pojo.model.SysDictData;
import com.bittrade.currency.api.service.ISysDictDataService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class SysDictDataServiceImpl extends DefaultSysDictDataServiceImpl<ISysDictDataDAO, SysDictData, SysDictDataDTO, SysDictDataVO> implements ISysDictDataService {
	
}

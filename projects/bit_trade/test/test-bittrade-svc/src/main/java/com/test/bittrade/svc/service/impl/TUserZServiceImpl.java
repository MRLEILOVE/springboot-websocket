package com.test.bittrade.svc.service.impl;

import org.springframework.stereotype.Service;

import com.test.bittrade.api.__default.service.impl.DefaultTUserZServiceImpl;
import com.test.bittrade.api.service.ITUserZService;
import com.test.bittrade.pojo.dto.TUserZDTO;
import com.test.bittrade.pojo.model.TUserZ;
import com.test.bittrade.pojo.vo.TUserZVO;
import com.test.bittrade.svc.dao.ITUserZDAO;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TUserZServiceImpl extends DefaultTUserZServiceImpl<TUserZ, TUserZDTO, TUserZVO, ITUserZDAO> implements ITUserZService {
	
}

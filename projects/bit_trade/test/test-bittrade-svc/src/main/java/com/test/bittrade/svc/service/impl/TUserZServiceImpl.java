package com.test.bittrade.svc.service.impl;

import org.springframework.stereotype.Service;

import com.test.bittrade.svc.api.__default.service.impl.DefaultTUserZServiceImpl;
import com.test.bittrade.svc.api.dao.ITUserZDAO;
import com.test.bittrade.svc.pojo.dto.TUserZDTO;
import com.test.bittrade.svc.pojo.vo.TUserZVO;
import com.test.bittrade.svc.pojo.model.TUserZ;
import com.test.bittrade.svc.api.service.ITUserZService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TUserZServiceImpl extends DefaultTUserZServiceImpl<TUserZ, TUserZDTO, TUserZVO, ITUserZDAO> implements ITUserZService {
	
}

package com.test.bittrade.svc.service.impl;

import org.springframework.stereotype.Service;

import com.test.bittrade.svc.api.__default.service.impl.DefaultTUserServiceImpl;
import com.test.bittrade.svc.api.dao.ITUserDAO;
import com.test.bittrade.svc.pojo.dto.TUserDTO;
import com.test.bittrade.svc.pojo.vo.TUserVO;
import com.test.bittrade.svc.pojo.model.TUser;
import com.test.bittrade.svc.api.service.ITUserService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TUserServiceImpl extends DefaultTUserServiceImpl<TUser, TUserDTO, TUserVO, ITUserDAO> implements ITUserService {
	
}

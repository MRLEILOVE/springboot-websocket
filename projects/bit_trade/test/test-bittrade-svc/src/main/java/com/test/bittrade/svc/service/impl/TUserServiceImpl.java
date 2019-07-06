package com.test.bittrade.svc.service.impl;

import org.springframework.stereotype.Service;

import com.test.bittrade.api.__default.service.impl.DefaultTUserServiceImpl;
import com.test.bittrade.api.__default.DAO.IDefaultTUserDAO;
import com.test.bittrade.pojo.dto.TUserDTO;
import com.test.bittrade.pojo.vo.TUserVO;
import com.test.bittrade.pojo.model.TUser;
import com.test.bittrade.api.service.ITUserService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TUserServiceImpl extends DefaultTUserServiceImpl<IDefaultTUserDAO, TUser, TUserDTO, TUserVO> implements ITUserService {
	
}

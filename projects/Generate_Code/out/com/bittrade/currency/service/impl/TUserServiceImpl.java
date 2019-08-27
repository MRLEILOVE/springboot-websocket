package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTUserServiceImpl;
import com.bittrade.currency.dao.ITUserDAO;
import com.bittrade.pojo.dto.TUserDTO;
import com.bittrade.pojo.vo.TUserVO;
import com.bittrade.pojo.model.TUser;
import com.bittrade.currency.api.service.ITUserService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TUserServiceImpl extends DefaultTUserServiceImpl<ITUserDAO, TUser, TUserDTO, TUserVO> implements ITUserService {
	
}

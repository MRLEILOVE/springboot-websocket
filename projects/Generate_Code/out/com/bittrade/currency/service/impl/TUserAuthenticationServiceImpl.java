package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTUserAuthenticationServiceImpl;
import com.bittrade.currency.dao.ITUserAuthenticationDAO;
import com.bittrade.pojo.dto.TUserAuthenticationDTO;
import com.bittrade.pojo.vo.TUserAuthenticationVO;
import com.bittrade.pojo.model.TUserAuthentication;
import com.bittrade.currency.api.service.ITUserAuthenticationService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TUserAuthenticationServiceImpl extends DefaultTUserAuthenticationServiceImpl<ITUserAuthenticationDAO, TUserAuthentication, TUserAuthenticationDTO, TUserAuthenticationVO> implements ITUserAuthenticationService {
	
}

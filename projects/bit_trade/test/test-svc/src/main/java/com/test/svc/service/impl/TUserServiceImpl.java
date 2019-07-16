package com.test.svc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.test.__default.service.impl.DefaultTUserServiceImpl;
import com.test.api.service.ITUserService;
import com.test.pojo.dto.TUserDTO;
import com.test.pojo.model.TUser;
import com.test.pojo.model.TUserInfo;
import com.test.pojo.vo.TUserVO;
import com.test.svc.dao.ITUserDAO;
import com.test.svc.dao.ITUserInfoDAO;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TUserServiceImpl extends DefaultTUserServiceImpl<ITUserDAO, TUser, TUserDTO, TUserVO> implements ITUserService {

	@Resource
	private ITUserDAO		userDAO;
	@Resource
	private ITUserInfoDAO	userInfoDAO;

	@Override
	public int modifyTrans(TUser user, TUserInfo userInfo) {
		System.out.println( "userDAO.modifyByPK=" + userDAO.modifyByPK( user ) );
		System.out.println( "userInfoDAO.add=" + userInfoDAO.add( userInfo ) );
		return 0;
	}

}

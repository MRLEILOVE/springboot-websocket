package com.test.bittrade.svc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.test.bittrade.api.__default.service.impl.DefaultTUserServiceImpl;
import com.test.bittrade.api.service.ITUserService;
import com.test.bittrade.pojo.dto.TUserDTO;
import com.test.bittrade.pojo.model.TUser;
import com.test.bittrade.pojo.model.TUserInfo;
import com.test.bittrade.pojo.vo.TUserVO;
import com.test.bittrade.svc.dao.ITUserDAO;
import com.test.bittrade.svc.dao.ITUserInfoDAO;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TUserServiceImpl extends DefaultTUserServiceImpl<TUser, TUserDTO, TUserVO, ITUserDAO> implements ITUserService {
	
	@Resource
	private ITUserDAO userDAO;
	@Resource
	private ITUserInfoDAO userInfoDAO;
	
	public int testTrans() {
		
		TUser tUser = new TUser() {
			private static final long serialVersionUID = 1L;
			{
				setId( 27 );
				setName( "王五-1" );
			}
		};
		System.out.println( "userDAO.modifyByPK=" + userDAO.modifyByPK( tUser ) );
		
		TUserInfo tUserInfo = new TUserInfo() {
			private static final long serialVersionUID = 1L;
			{
				setUserId( tUser.getId() );
				setAge( 17 );
			}
		};
		System.out.println( "userInfoDAO.add=" + userInfoDAO.add( tUserInfo ) );
		
		return 0;
	}
	
}

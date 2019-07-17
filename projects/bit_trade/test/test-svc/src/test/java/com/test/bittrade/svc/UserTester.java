package com.test.bittrade.svc;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.api.service.ITUserService;
import com.test.bittrade.svc.base.BaseTester;
import com.test.pojo.model.TUser;
import com.test.pojo.model.TUserInfo;
import com.test.svc.service.impl.TUserServiceImpl;

public class UserTester extends BaseTester {

	@Autowired
	private ITUserService		userService;
	@Autowired
	private TUserServiceImpl	userServiceImpl;

	@Test
	public void test() {
		TUser user = new TUser();
		user.setName( "zs张三" );
		// boolean flag = userService.save(user);
		// System.out.println(flag);
		System.out.println( userService.add( user ) );
		System.out.println( userService.removeByPK( 28 ) );
		TUser user2 = new TUser();
		// user2.setId(30);
		user2.setName( "22" );
		TUser userCondi = new TUser();
		userCondi.setName( "zs张三" );
		// System.out.println(userService.modifyByPK(user2));
		System.out.println( userService.modifyBy( user2, userCondi ) );
	}

	@SuppressWarnings("serial")
	@Test
	public void modify() {
		// int id = 20;
		// userService.modify(id, "李四", 20);
		// userService.modify(id, "王五", 21);

		TUser model = new TUser() {
			{
				setId( 30 );
				setName( "name-2" );
			}
		};
		System.out.println( "==" + userService.modifyByPK( model ) );
		TUser condiModel = new TUser() {
			{
				setName( "22" );
			}
		};
		System.out.println( "=" + userService.modifyBy( model, condiModel ) );
	}

	@Test
	public void get_() {
		System.out.println( userService.getByPK( 35 ) );
		TUser u = new TUser();
		// u.setName("22");
		// System.out.println(userService.get(u));
		System.out.println( "==" + userService.getsByPage( u, 1, 3 ) );
	}

	@Test
	public void testTrans() {
		int i_age = 18;
		TUser tUser = new TUser() {
			private static final long serialVersionUID = 1L;
			{
				setId( 27 );
				setName( "王五-" + i_age );
			}
		};
		TUserInfo tUserInfo = new TUserInfo() {
			private static final long serialVersionUID = 1L;
			{
				setUserId( tUser.getId() );
				setAge( i_age );
			}
		};
		try {
//			System.out.println( "userService.modifyWithTrans=" + userService.modifyWithTrans( tUser, tUserInfo ) );
			System.out.println( "userServiceImpl.modifyWithTrans2=" + userServiceImpl.modifyWithTrans2( tUser, tUserInfo ) );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

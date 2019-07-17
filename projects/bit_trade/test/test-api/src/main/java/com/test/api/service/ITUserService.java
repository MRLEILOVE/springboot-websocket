package com.test.api.service;

import com.test.__default.service.IDefaultTUserService;
import com.test.pojo.dto.TUserDTO;
import com.test.pojo.model.TUser;
import com.test.pojo.model.TUserInfo;
import com.test.pojo.vo.TUserVO;

/**
 * 
 * @author Administrator
 *
 */
public interface ITUserService extends IDefaultTUserService<TUser, TUserDTO, TUserVO> {

	/**
	 * <p>
	 * 测试事务？
	 * </p>
	 * testTrans:(这里用一句话描述这个方法的作用). <br/>  
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>  
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>  
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>  
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>  
	 *  
	 * @author Administrator  
	 * @param user
	 * @param userInfo
	 * @return  
	 * @throws Exception 
	 * @since JDK 1.8
	 */
//	@org.springframework.transaction.annotation.Transactional(rollbackFor = { IndexOutOfBoundsException.class })
//	@org.springframework.transaction.annotation.Transactional(noRollbackFor = { Exception.class })
	int modifyWithTrans(TUser user, TUserInfo userInfo) throws Exception;

//	int modifyWithTrans2(TUser user, TUserInfo userInfo) throws Exception;
	
}

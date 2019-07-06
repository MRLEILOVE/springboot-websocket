package com.test.bittrade.svc.api.service;

import com.test.bittrade.svc.api.__default.service.IDefaultTUserService;
import com.test.bittrade.svc.api.dao.ITUserDAO;
import com.test.bittrade.svc.pojo.dto.TUserDTO;
import com.test.bittrade.svc.pojo.vo.TUserVO;
import com.test.bittrade.svc.pojo.model.TUser;

/**
 * 
 * @author Administrator
 *
 */
public interface ITUserService extends IDefaultTUserService<TUser, TUserDTO, TUserVO, ITUserDAO> {
	
}

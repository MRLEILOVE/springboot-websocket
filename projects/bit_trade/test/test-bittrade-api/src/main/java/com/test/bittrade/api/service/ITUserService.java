package com.test.bittrade.api.service;

import com.test.bittrade.api.__default.service.IDefaultTUserService;
import com.test.bittrade.api.__default.DAO.IDefaultTUserDAO;
import com.test.bittrade.pojo.dto.TUserDTO;
import com.test.bittrade.pojo.vo.TUserVO;
import com.test.bittrade.pojo.model.TUser;

/**
 * 
 * @author Administrator
 *
 */
public interface ITUserService extends IDefaultTUserService<TUser, TUserDTO, TUserVO, IDefaultTUserDAO> {
	
}

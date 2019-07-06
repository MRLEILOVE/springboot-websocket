package com.test.bittrade.api.service;

import com.test.bittrade.api.__default.service.IDefaultTUserInfoService;
import com.test.bittrade.api.__default.DAO.IDefaultTUserInfoDAO;
import com.test.bittrade.pojo.dto.TUserInfoDTO;
import com.test.bittrade.pojo.vo.TUserInfoVO;
import com.test.bittrade.pojo.model.TUserInfo;

/**
 * 
 * @author Administrator
 *
 */
public interface ITUserInfoService extends IDefaultTUserInfoService<TUserInfo, TUserInfoDTO, TUserInfoVO, IDefaultTUserInfoDAO> {
	
}

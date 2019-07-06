package com.test.bittrade.svc.api.service;

import com.test.bittrade.svc.api.__default.service.IDefaultTUserInfoService;
import com.test.bittrade.svc.api.dao.ITUserInfoDAO;
import com.test.bittrade.svc.pojo.dto.TUserInfoDTO;
import com.test.bittrade.svc.pojo.vo.TUserInfoVO;
import com.test.bittrade.svc.pojo.model.TUserInfo;

/**
 * 
 * @author Administrator
 *
 */
public interface ITUserInfoService extends IDefaultTUserInfoService<TUserInfo, TUserInfoDTO, TUserInfoVO, ITUserInfoDAO> {
	
}

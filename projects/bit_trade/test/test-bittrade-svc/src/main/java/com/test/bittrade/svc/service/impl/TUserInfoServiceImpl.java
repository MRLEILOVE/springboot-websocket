package com.test.bittrade.svc.service.impl;

import org.springframework.stereotype.Service;

import com.test.bittrade.svc.api.__default.service.impl.DefaultTUserInfoServiceImpl;
import com.test.bittrade.svc.api.dao.ITUserInfoDAO;
import com.test.bittrade.svc.pojo.dto.TUserInfoDTO;
import com.test.bittrade.svc.pojo.vo.TUserInfoVO;
import com.test.bittrade.svc.pojo.model.TUserInfo;
import com.test.bittrade.svc.api.service.ITUserInfoService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TUserInfoServiceImpl extends DefaultTUserInfoServiceImpl<TUserInfo, TUserInfoDTO, TUserInfoVO, ITUserInfoDAO> implements ITUserInfoService {
	
}

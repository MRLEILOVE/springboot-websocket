package com.test.bittrade.svc.service.impl;

import org.springframework.stereotype.Service;

import com.test.bittrade.api.__default.service.impl.DefaultTUserInfoServiceImpl;
import com.test.bittrade.api.service.ITUserInfoService;
import com.test.bittrade.pojo.dto.TUserInfoDTO;
import com.test.bittrade.pojo.model.TUserInfo;
import com.test.bittrade.pojo.vo.TUserInfoVO;
import com.test.bittrade.svc.dao.ITUserInfoDAO;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TUserInfoServiceImpl extends DefaultTUserInfoServiceImpl<TUserInfo, TUserInfoDTO, TUserInfoVO, ITUserInfoDAO> implements ITUserInfoService<ITUserInfoDAO> {
	
}

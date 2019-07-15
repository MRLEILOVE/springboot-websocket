package com.test.svc.service.impl;

import org.springframework.stereotype.Service;

import com.test.__default.service.impl.DefaultTUserInfoServiceImpl;
import com.test.svc.dao.ITUserInfoDAO;
import com.test.pojo.dto.TUserInfoDTO;
import com.test.pojo.vo.TUserInfoVO;
import com.test.pojo.model.TUserInfo;
import com.test.api.service.ITUserInfoService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TUserInfoServiceImpl extends DefaultTUserInfoServiceImpl<ITUserInfoDAO, TUserInfo, TUserInfoDTO, TUserInfoVO> implements ITUserInfoService {
	
}

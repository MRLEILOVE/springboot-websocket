package com.test.svc.service.impl;

import org.springframework.stereotype.Service;

import com.test.__default.service.impl.DefaultTUserZServiceImpl;
import com.test.svc.dao.ITUserZDAO;
import com.test.pojo.dto.TUserZDTO;
import com.test.pojo.vo.TUserZVO;
import com.test.pojo.model.TUserZ;
import com.test.api.service.ITUserZService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TUserZServiceImpl extends DefaultTUserZServiceImpl<ITUserZDAO, TUserZ, TUserZDTO, TUserZVO> implements ITUserZService {
	
}

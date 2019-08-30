package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTAccountManageServiceImpl;
import com.bittrade.currency.api.service.ITAccountManageService;
import com.bittrade.currency.dao.ITAccountManageDAO;
import com.bittrade.pojo.dto.TAccountManageDTO;
import com.bittrade.pojo.model.TAccountManage;
import com.bittrade.pojo.vo.TAccountManageVO;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TAccountManageServiceImpl extends DefaultTAccountManageServiceImpl<ITAccountManageDAO, TAccountManage, TAccountManageDTO, TAccountManageVO> implements ITAccountManageService {
	
}

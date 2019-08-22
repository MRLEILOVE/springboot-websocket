package com.bittrade.admin.service.impl.sys;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTAccountManageServiceImpl;
import com.bittrade.pojo.dto.TAccountManageDTO;
import com.bittrade.pojo.vo.TAccountManageVO;
import com.bittrade.pojo.model.TAccountManage;
import com.bittrade.admin.dao.sys.ITAccountManageDAO;
import com.bittrade.admin.service.sys.ITAccountManageService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TAccountManageServiceImpl extends DefaultTAccountManageServiceImpl<ITAccountManageDAO, TAccountManage, TAccountManageDTO, TAccountManageVO> implements ITAccountManageService {
	
}

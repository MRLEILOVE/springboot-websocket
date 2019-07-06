package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.api.__default.DAO.IDefaultTEntrustDAO;
import com.bittrade.api.__default.service.impl.DefaultTEntrustServiceImpl;
import com.bittrade.api.service.ITEntrustService;
import com.bittrade.pojo.dto.TEntrustDTO;
import com.bittrade.pojo.model.TEntrust;
import com.bittrade.pojo.vo.TEntrustVO;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TEntrustServiceImpl extends DefaultTEntrustServiceImpl<IDefaultTEntrustDAO, TEntrust, TEntrustDTO, TEntrustVO> implements ITEntrustService {
	
}

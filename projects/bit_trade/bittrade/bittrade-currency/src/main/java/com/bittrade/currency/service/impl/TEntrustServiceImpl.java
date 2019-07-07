package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.api.__default.service.impl.DefaultTEntrustServiceImpl;
import com.bittrade.currency.dao.ITEntrustDAO;
import com.bittrade.pojo.dto.TEntrustDTO;
import com.bittrade.pojo.vo.TEntrustVO;
import com.bittrade.pojo.model.TEntrust;
import com.bittrade.api.service.ITEntrustService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TEntrustServiceImpl extends DefaultTEntrustServiceImpl<ITEntrustDAO, TEntrust, TEntrustDTO, TEntrustVO> implements ITEntrustService<TEntrust, TEntrustDTO, TEntrustVO, ITEntrustDAO> {
	
}

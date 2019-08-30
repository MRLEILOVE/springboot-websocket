package com.bittrade.admin.service.impl.entrust;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTEntrustServiceImpl;
import com.bittrade.pojo.dto.TEntrustDTO;
import com.bittrade.pojo.vo.TEntrustVO;
import com.bittrade.pojo.model.TEntrust;
import com.bittrade.admin.dao.entrust.ITEntrustDAO;
import com.bittrade.admin.service.entrust.ITEntrustService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TEntrustServiceImpl extends DefaultTEntrustServiceImpl<ITEntrustDAO, TEntrust, TEntrustDTO, TEntrustVO> implements ITEntrustService {
	
}

package com.bittrade.admin.service.impl.wallet;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTOrderServiceImpl;
import com.bittrade.pojo.dto.TOrderDTO;
import com.bittrade.pojo.vo.TOrderVO;
import com.bittrade.pojo.model.TOrder;
import com.bittrade.admin.dao.wallet.ITOrderDAO;
import com.bittrade.admin.service.wallet.ITOrderService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TOrderServiceImpl extends DefaultTOrderServiceImpl<ITOrderDAO, TOrder, TOrderDTO, TOrderVO> implements ITOrderService {
	
}

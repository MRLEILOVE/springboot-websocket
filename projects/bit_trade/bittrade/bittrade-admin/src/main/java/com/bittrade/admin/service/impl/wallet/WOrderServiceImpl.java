package com.bittrade.admin.service.impl.wallet;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultWOrderServiceImpl;
import com.bittrade.pojo.dto.WOrderDTO;
import com.bittrade.pojo.vo.WOrderVO;
import com.bittrade.pojo.model.WOrder;
import com.bittrade.admin.dao.wallet.IWOrderDAO;
import com.bittrade.admin.service.wallet.IWOrderService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class WOrderServiceImpl extends DefaultWOrderServiceImpl<IWOrderDAO, WOrder, WOrderDTO, WOrderVO> implements IWOrderService {
	
}
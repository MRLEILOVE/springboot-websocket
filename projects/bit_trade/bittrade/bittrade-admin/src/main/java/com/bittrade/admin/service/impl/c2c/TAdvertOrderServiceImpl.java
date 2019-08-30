package com.bittrade.admin.service.impl.c2c;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTAdvertOrderServiceImpl;
import com.bittrade.admin.dao.c2c.ITAdvertOrderDAO;
import com.bittrade.admin.service.c2c.ITAdvertOrderService;
import com.bittrade.pojo.dto.TAdvertOrderDTO;
import com.bittrade.pojo.model.TAdvertOrder;
import com.bittrade.pojo.vo.TAdvertOrderVO;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TAdvertOrderServiceImpl extends DefaultTAdvertOrderServiceImpl<ITAdvertOrderDAO, TAdvertOrder, TAdvertOrderDTO, TAdvertOrderVO> implements ITAdvertOrderService {
	
}

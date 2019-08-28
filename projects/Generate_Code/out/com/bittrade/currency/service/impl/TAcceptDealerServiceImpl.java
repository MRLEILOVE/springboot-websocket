package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTAcceptDealerServiceImpl;
import com.bittrade.currency.dao.ITAcceptDealerDAO;
import com.bittrade.pojo.dto.TAcceptDealerDTO;
import com.bittrade.pojo.vo.TAcceptDealerVO;
import com.bittrade.pojo.model.TAcceptDealer;
import com.bittrade.currency.api.service.ITAcceptDealerService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TAcceptDealerServiceImpl extends DefaultTAcceptDealerServiceImpl<ITAcceptDealerDAO, TAcceptDealer, TAcceptDealerDTO, TAcceptDealerVO> implements ITAcceptDealerService {
	
}

package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTPaymentModeServiceImpl;
import com.bittrade.currency.dao.ITPaymentModeDAO;
import com.bittrade.pojo.dto.TPaymentModeDTO;
import com.bittrade.pojo.vo.TPaymentModeVO;
import com.bittrade.pojo.model.TPaymentMode;
import com.bittrade.currency.api.service.ITPaymentModeService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TPaymentModeServiceImpl extends DefaultTPaymentModeServiceImpl<ITPaymentModeDAO, TPaymentMode, TPaymentModeDTO, TPaymentModeVO> implements ITPaymentModeService {
	
}

package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTPaymentServiceImpl;
import com.bittrade.currency.dao.ITPaymentDAO;
import com.bittrade.pojo.dto.TPaymentDTO;
import com.bittrade.pojo.vo.TPaymentVO;
import com.bittrade.pojo.model.TPayment;
import com.bittrade.currency.api.service.ITPaymentService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TPaymentServiceImpl extends DefaultTPaymentServiceImpl<ITPaymentDAO, TPayment, TPaymentDTO, TPaymentVO> implements ITPaymentService {
	
}

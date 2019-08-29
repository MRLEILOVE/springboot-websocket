package com.bittrade.c2c.service.impl;

import com.bittrade.__default.service.impl.DefaultTPaymentModeServiceImpl;
import com.bittrade.c2c.dao.ITPaymentModeDAO;
import com.bittrade.c2c.service.ITPaymentModeService;
import com.bittrade.pojo.dto.TPaymentModeDTO;
import com.bittrade.pojo.model.TPaymentMode;
import com.bittrade.pojo.vo.TPaymentModeVO;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TPaymentModeServiceImpl extends DefaultTPaymentModeServiceImpl<ITPaymentModeDAO, TPaymentMode, TPaymentModeDTO, TPaymentModeVO> implements ITPaymentModeService {
	
}

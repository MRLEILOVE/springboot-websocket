package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTAdvertInfoServiceImpl;
import com.bittrade.currency.dao.ITAdvertInfoDAO;
import com.bittrade.pojo.dto.TAdvertInfoDTO;
import com.bittrade.pojo.vo.TAdvertInfoVO;
import com.bittrade.pojo.model.TAdvertInfo;
import com.bittrade.currency.api.service.ITAdvertInfoService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TAdvertInfoServiceImpl extends DefaultTAdvertInfoServiceImpl<ITAdvertInfoDAO, TAdvertInfo, TAdvertInfoDTO, TAdvertInfoVO> implements ITAdvertInfoService {
	
}

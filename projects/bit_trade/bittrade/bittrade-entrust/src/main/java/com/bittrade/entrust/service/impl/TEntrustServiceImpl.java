package com.bittrade.entrust.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bittrade.api.service.ITEntrustService;
import com.bittrade.api.service.abs.AbstractTEntrustServiceImpl;
import com.bittrade.entrust.dao.ITEntrustDAO;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TEntrustServiceImpl extends AbstractTEntrustServiceImpl<ITEntrustDAO> implements ITEntrustService<ITEntrustDAO> {

	@Autowired
	private ITEntrustDAO entrustDAO;
	
	@Override
	public void updateOnMatch(BigDecimal successAmount, BigDecimal leftCount, int status, long ID) {
		entrustDAO.updateOnMatch(successAmount, leftCount, status, ID);
	}
	
}

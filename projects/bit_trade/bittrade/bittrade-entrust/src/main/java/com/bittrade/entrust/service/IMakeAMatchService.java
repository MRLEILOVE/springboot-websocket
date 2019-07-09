package com.bittrade.entrust.service;

import com.bittrade.api.service.ITEntrustService;
import com.bittrade.entrust.dao.ITEntrustDAO;
import com.bittrade.pojo.model.TEntrust;

/**
 * 撮合？
 * @author Administrator
 *
 */
public interface IMakeAMatchService {

	/**
	 * 
	 * @param entrust
	 * @param entrustService
	 */
	void makeAMatch(TEntrust entrust, ITEntrustService<ITEntrustDAO> entrustService);
	
}

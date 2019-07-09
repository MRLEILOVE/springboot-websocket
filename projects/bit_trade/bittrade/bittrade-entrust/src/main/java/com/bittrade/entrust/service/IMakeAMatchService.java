package com.bittrade.entrust.service;

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
	 */
	void makeAMatch(TEntrust entrust);
	
}

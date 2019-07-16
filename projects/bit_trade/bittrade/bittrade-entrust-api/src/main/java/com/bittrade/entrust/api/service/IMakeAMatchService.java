package com.bittrade.entrust.api.service;

import com.bittrade.pojo.model.TEntrust;

/**
 * 撮合？
 * @author Administrator
 *
 */
public interface IMakeAMatchService {

	/**
	 * 撮合
	 * @param entrust
	 */
	void makeAMatch(TEntrust entrust);
	
}

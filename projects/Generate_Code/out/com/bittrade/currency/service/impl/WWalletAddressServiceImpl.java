package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultWWalletAddressServiceImpl;
import com.bittrade.currency.dao.IWWalletAddressDAO;
import com.bittrade.pojo.dto.WWalletAddressDTO;
import com.bittrade.pojo.vo.WWalletAddressVO;
import com.bittrade.pojo.model.WWalletAddress;
import com.bittrade.currency.api.service.IWWalletAddressService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class WWalletAddressServiceImpl extends DefaultWWalletAddressServiceImpl<IWWalletAddressDAO, WWalletAddress, WWalletAddressDTO, WWalletAddressVO> implements IWWalletAddressService {
	
}

package com.bittrade.admin.service.impl.wallet;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultWWalletAddressServiceImpl;
import com.bittrade.pojo.dto.WWalletAddressDTO;
import com.bittrade.pojo.vo.WWalletAddressVO;
import com.bittrade.pojo.model.WWalletAddress;
import com.bittrade.admin.dao.wallet.IWWalletAddressDAO;
import com.bittrade.admin.service.wallet.IWWalletAddressService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class WWalletAddressServiceImpl extends DefaultWWalletAddressServiceImpl<IWWalletAddressDAO, WWalletAddress, WWalletAddressDTO, WWalletAddressVO> implements IWWalletAddressService {
	
}

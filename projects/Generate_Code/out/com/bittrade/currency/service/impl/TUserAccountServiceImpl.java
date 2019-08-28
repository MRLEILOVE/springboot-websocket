package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTUserAccountServiceImpl;
import com.bittrade.currency.dao.ITUserAccountDAO;
import com.bittrade.pojo.dto.TUserAccountDTO;
import com.bittrade.pojo.vo.TUserAccountVO;
import com.bittrade.pojo.model.TUserAccount;
import com.bittrade.currency.api.service.ITUserAccountService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TUserAccountServiceImpl extends DefaultTUserAccountServiceImpl<ITUserAccountDAO, TUserAccount, TUserAccountDTO, TUserAccountVO> implements ITUserAccountService {
	
}

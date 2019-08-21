package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTTransferDirectionServiceImpl;
import com.bittrade.currency.dao.ITTransferDirectionDAO;
import com.bittrade.pojo.dto.TTransferDirectionDTO;
import com.bittrade.pojo.vo.TTransferDirectionVO;
import com.bittrade.pojo.model.TTransferDirection;
import com.bittrade.currency.api.service.ITTransferDirectionService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TTransferDirectionServiceImpl extends DefaultTTransferDirectionServiceImpl<ITTransferDirectionDAO, TTransferDirection, TTransferDirectionDTO, TTransferDirectionVO> implements ITTransferDirectionService {
	
}

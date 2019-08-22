package com.bittrade.admin.service.impl.wallet;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTTransferDirectionServiceImpl;
import com.bittrade.pojo.dto.TTransferDirectionDTO;
import com.bittrade.pojo.vo.TTransferDirectionVO;
import com.bittrade.pojo.model.TTransferDirection;
import com.bittrade.admin.dao.wallet.ITTransferDirectionDAO;
import com.bittrade.admin.service.wallet.ITTransferDirectionService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TTransferDirectionServiceImpl extends DefaultTTransferDirectionServiceImpl<ITTransferDirectionDAO, TTransferDirection, TTransferDirectionDTO, TTransferDirectionVO> implements ITTransferDirectionService {
	
}

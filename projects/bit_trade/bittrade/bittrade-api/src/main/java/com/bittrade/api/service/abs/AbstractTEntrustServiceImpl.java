package com.bittrade.api.service.abs;

import java.math.BigDecimal;

import com.bittrade.api.__default.service.impl.DefaultTEntrustServiceImpl;
import com.bittrade.api.service.ITEntrustService;
import com.bittrade.pojo.dto.TEntrustDTO;
import com.bittrade.pojo.model.TEntrust;
import com.bittrade.pojo.vo.TEntrustVO;
import com.core.framework.base.DAO.IBaseDAO;

/**
 * 
 * @author Administrator
 *
 */
// public abstract class AbstractTEntrustServiceImpl extends
// DefaultTEntrustServiceImpl<ITEntrustDAO, TEntrust, TEntrustDTO, TEntrustVO> implements
// ITEntrustService {

public abstract class AbstractTEntrustServiceImpl<DAO extends IBaseDAO<TEntrust, TEntrustDTO, TEntrustVO>>
		extends DefaultTEntrustServiceImpl<DAO, TEntrust, TEntrustDTO, TEntrustVO> implements ITEntrustService<DAO> {

	@Override
	public void updateOnMatch(BigDecimal successAmount, BigDecimal leftCount, int status, long ID) {
		throw new RuntimeException( "unfulfilment" );
	}

}

package com.bittrade.api.service.abs;

import java.util.List;

import com.bittrade.api.__default.DAO.IDefaultTKlineDAO;
import com.bittrade.api.__default.service.impl.DefaultTKlineServiceImpl;
import com.bittrade.api.service.ITKlineService;
import com.bittrade.pojo.dto.QueryKLineDto;
import com.bittrade.pojo.dto.TKlineDTO;
import com.bittrade.pojo.model.TKline;
import com.bittrade.pojo.vo.QueryKLineVO;
import com.bittrade.pojo.vo.TKlineVO;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.DTO.BaseDTO;
import com.core.framework.base.VO.BaseVO;
import com.core.framework.base.model.BaseModel;

/**
 * 
 * @author Administrator
 *
 */
// public abstract class AbstractTKlineServiceImpl extends
// DefaultTKlineServiceImpl<ITKlineDAO, TKline, TKlineDTO, TKlineVO> implements
// ITKlineService {

public abstract class AbstractTKlineServiceImpl<DAO extends IBaseDAO<Model, DTO, VO>, Model extends BaseModel<Model>, DTO extends BaseDTO<DTO>, VO extends BaseVO<VO>>
		extends DefaultTKlineServiceImpl<IDefaultTKlineDAO, TKline, TKlineDTO, TKlineVO> implements ITKlineService {

	@Override
	public List<QueryKLineVO> queryKLine(QueryKLineDto queryKLineDto) {
		throw new RuntimeException( "unfulfilment" );
	}

}

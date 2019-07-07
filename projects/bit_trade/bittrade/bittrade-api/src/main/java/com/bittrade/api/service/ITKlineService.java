package com.bittrade.api.service;

import java.util.List;

import com.bittrade.api.__default.service.IDefaultTKlineService;
import com.bittrade.pojo.dto.QueryKLineDto;
import com.bittrade.pojo.vo.QueryKLineVO;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.DTO.BaseDTO;
import com.core.framework.base.VO.BaseVO;
import com.core.framework.base.model.BaseModel;

/**
 * 
 * @author Administrator
 *
 */
public interface ITKlineService<Model extends BaseModel<Model>, DTO extends BaseDTO<DTO>, VO extends BaseVO<VO>, DAO extends IBaseDAO<Model, DTO, VO>> extends IDefaultTKlineService<Model, DTO, VO, DAO> {

    /**
     * k线查询
     */
    List<QueryKLineVO> queryKLine(QueryKLineDto queryKLineDto);
}

package com.test.bittrade.api.service;

import com.core.framework.base.DAO.IBaseDAO;
import com.test.bittrade.api.__default.service.IDefaultTUserZService;
import com.test.bittrade.pojo.dto.TUserZDTO;
import com.test.bittrade.pojo.model.TUserZ;
import com.test.bittrade.pojo.vo.TUserZVO;

/**
 * 
 * @author Administrator
 *
 */
public interface ITUserZService
//<Model extends BaseModel<Model>, DTO extends BaseDTO<DTO>, VO extends BaseVO<VO>, DAO extends IBaseDAO<Model, DTO, VO>>
<DAO extends IBaseDAO<TUserZ, TUserZDTO, TUserZVO>>
extends IDefaultTUserZService<TUserZ, TUserZDTO, TUserZVO, DAO> {
	
}

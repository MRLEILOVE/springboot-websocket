package com.test.bittrade.api.service;

import com.core.framework.base.DAO.IBaseDAO;
import com.test.bittrade.api.__default.service.IDefaultTUserInfoService;
import com.test.bittrade.pojo.dto.TUserInfoDTO;
import com.test.bittrade.pojo.model.TUserInfo;
import com.test.bittrade.pojo.vo.TUserInfoVO;

/**
 * 
 * @author Administrator
 *
 */
public interface ITUserInfoService
//<Model extends BaseModel<Model>, DTO extends BaseDTO<DTO>, VO extends BaseVO<VO>, DAO extends IBaseDAO<Model, DTO, VO>>
<DAO extends IBaseDAO<TUserInfo, TUserInfoDTO, TUserInfoVO>>
extends IDefaultTUserInfoService<TUserInfo, TUserInfoDTO, TUserInfoVO, DAO> {
	
}

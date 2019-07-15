package com.test.bittrade.api.service;

import com.test.bittrade.api.__default.service.IDefaultTUserService;
import com.test.bittrade.pojo.dto.TUserDTO;
import com.test.bittrade.pojo.model.TUser;
import com.test.bittrade.pojo.vo.TUserVO;

/**
 * 
 * @author Administrator
 *
 */
public interface ITUserService
//<Model extends BaseModel<Model>, DTO extends BaseDTO<DTO>, VO extends BaseVO<VO>, DAO extends IBaseDAO<Model, DTO, VO>>
extends IDefaultTUserService<TUser, TUserDTO, TUserVO> {
	
	/**
	 * <p>
	 * 测试事务？
	 * </p>
	 * testTrans:(这里用一句话描述这个方法的作用). <br/>  
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>  
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>  
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>  
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>  
	 *  
	 * @author Administrator  
	 * @return  
	 * @since JDK 1.8
	 */
	int testTrans();
	
}

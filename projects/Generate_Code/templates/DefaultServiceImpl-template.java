/**
 * This code is generated automatically. Please do not edit it.
 */
package ${API_BASE_PKG}.__default.service.impl${module_name};

import ${API_BASE_PKG}.__default.service${module_name}.IDefault${struct.className}Service;
import ${FRAMEWORK_PACKAGE}.base.DAO${module_name}.IBaseDAO;
import ${FRAMEWORK_PACKAGE}.base.DTO${module_name}.BaseDTO;
import ${FRAMEWORK_PACKAGE}.base.VO${module_name}.BaseVO;
import ${FRAMEWORK_PACKAGE}.base.model${module_name}.BaseModel;
import ${FRAMEWORK_PACKAGE}.base.service.impl.BaseServiceImpl;

/**
 * 
 * @author Administrator
 *
 */
//public abstract class Default${struct.className}ServiceImpl extends BaseServiceImpl<${struct.className}, ${struct.className}DTO, ${struct.className}VO, I${struct.className}DAO> implements IDefault${struct.className}Service {
public abstract class Default${struct.className}ServiceImpl<DAO extends IBaseDAO<Model, DTO, VO>, Model extends BaseModel<Model>, DTO extends BaseDTO<DTO>, VO extends BaseVO<VO>> extends BaseServiceImpl<Model, DTO, VO, DAO> implements IDefault${struct.className}Service<Model, DTO, VO, DAO> {
	
}

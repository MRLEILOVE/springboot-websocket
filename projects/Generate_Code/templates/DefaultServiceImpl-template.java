/**
 * This code is generated automatically. Please do not edit it.
 */
package ${DEFAULT_BASE_PKG}.service.impl${module_name};

<#--
import ${FRAMEWORK_PACKAGE}.base.model${module_name}.BaseModel;
import ${FRAMEWORK_PACKAGE}.base.DTO${module_name}.BaseDTO;
import ${FRAMEWORK_PACKAGE}.base.VO${module_name}.BaseVO;
-->
import ${POJO_BASE_PKG}.model.${struct.className};
import ${POJO_BASE_PKG}.${DTO}.${struct.className}DTO;
import ${POJO_BASE_PKG}.${VO}.${struct.className}VO;
import ${DEFAULT_BASE_PKG}.service${module_name}.IDefault${struct.className}Service;
import ${FRAMEWORK_PACKAGE}.base.DAO${module_name}.IBaseDAO;
import ${FRAMEWORK_PACKAGE}.base.service.impl.BaseServiceImpl;

/**
 * 
 * @author Administrator
 *
 */
//public abstract class Default${struct.className}ServiceImpl extends BaseServiceImpl<${struct.className}, ${struct.className}DTO, ${struct.className}VO, I${struct.className}DAO> implements IDefault${struct.className}Service {
public abstract class Default${struct.className}ServiceImpl<DAO extends IBaseDAO<${struct.className}, ${struct.className}DTO, ${struct.className}VO>> extends BaseServiceImpl<${struct.className}, ${struct.className}DTO, ${struct.className}VO, DAO> implements IDefault${struct.className}Service {
	
}

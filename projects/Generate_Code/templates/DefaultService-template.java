/**
 * This code is generated automatically. Please do not edit it.
 */
package ${DEFAULT_BASE_PKG}.service${module_name};

<#--
import ${FRAMEWORK_PACKAGE}.base.model${module_name}.BaseModel;
import ${FRAMEWORK_PACKAGE}.base.DTO${module_name}.BaseDTO;
import ${FRAMEWORK_PACKAGE}.base.VO${module_name}.BaseVO;
import ${FRAMEWORK_PACKAGE}.base.DAO${module_name}.IBaseDAO;
-->
import ${POJO_BASE_PKG}.model.${struct.className};
import ${POJO_BASE_PKG}.${DTO}.${struct.className}DTO;
import ${POJO_BASE_PKG}.${VO}.${struct.className}VO;
import ${FRAMEWORK_PACKAGE}.base.service.IBaseService;

/**
 * 
 * @author Administrator
 *
 */
<#--
public abstract interface IDefault${struct.className}Service extends IBaseService<${struct.className}, ${struct.className}DTO, ${struct.className}VO, I${struct.className}DAO> {
, DAO extends IBaseDAO<Model, DTO, VO>
}
-->
public abstract interface IDefault${struct.className}Service extends IBaseService<${struct.className}, ${struct.className}DTO, ${struct.className}VO> {
	
}

package ${API_BASE_PKG}.service${module_name};

<#--
import ${FRAMEWORK_PACKAGE}.base.DAO.IBaseDAO;
import ${FRAMEWORK_PACKAGE}.base.DTO.BaseDTO;
import ${FRAMEWORK_PACKAGE}.base.VO.BaseVO;
import ${FRAMEWORK_PACKAGE}.base.model.BaseModel;
-->
import ${DEFAULT_BASE_PKG}.service${module_name}.IDefault${struct.className}Service;
<#--
import ${DEFAULT_BASE_PKG}.DAO${module_name}.IDefault${struct.className}DAO;
-->
import ${POJO_BASE_PKG}.${DTO}${module_name}.${struct.className}DTO;
import ${POJO_BASE_PKG}.${VO}${module_name}.${struct.className}VO;
import ${POJO_BASE_PKG}.model${module_name}.${struct.className};

/**
 * 
 * @author Administrator
 *
 */
<#--
<${struct.className}, ${struct.className}DTO, ${struct.className}VO, I${struct.className}DAO>
I${struct.className}DAO, ${struct.className}, ${struct.className}DTO, ${struct.className}VO
Model extends BaseModel<Model>, DTO extends BaseDTO<DTO>, VO extends BaseVO<VO>, 
<DAO extends IBaseDAO<${struct.className}, ${struct.className}DTO, ${struct.className}VO>>
<DAO extends IBaseDAO<TUserInfo, TUserInfoDTO, TUserInfoVO>>
-->
public interface I${struct.className}Service extends IDefault${struct.className}Service<${struct.className}, ${struct.className}DTO, ${struct.className}VO> {
	
}

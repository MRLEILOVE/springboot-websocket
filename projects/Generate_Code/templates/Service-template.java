package ${API_BASE_PKG}.service${module_name};

import com.core.framework.base.DAO.IBaseDAO;
<#--
import com.core.framework.base.DTO.BaseDTO;
import com.core.framework.base.VO.BaseVO;
import com.core.framework.base.model.BaseModel;
-->

import ${API_BASE_PKG}.__default.service${module_name}.IDefault${struct.className}Service;
<#--
import ${API_BASE_PKG}.__default.DAO${module_name}.IDefault${struct.className}DAO;
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
-->
public interface I${struct.className}Service<DAO extends IBaseDAO<${struct.className}, ${struct.className}DTO, ${struct.className}VO>> extends IDefault${struct.className}Service<${struct.className}, ${struct.className}DTO, ${struct.className}VO, DAO> {
	
}

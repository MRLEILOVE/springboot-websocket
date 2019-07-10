package ${BASE_PKG}.service.impl${module_name};

import org.springframework.stereotype.Service;

import ${API_BASE_PKG}.__default.service.impl${module_name}.Default${struct.className}ServiceImpl;
import ${BASE_PKG}.${DAO}${module_name}.I${struct.className}DAO;
import ${POJO_BASE_PKG}.${DTO}${module_name}.${struct.className}DTO;
import ${POJO_BASE_PKG}.${VO}${module_name}.${struct.className}VO;
import ${POJO_BASE_PKG}.model${module_name}.${struct.className};
import ${API_BASE_PKG}.service${module_name}.I${struct.className}Service;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class ${struct.className}ServiceImpl extends Default${struct.className}ServiceImpl<I${struct.className}DAO, ${struct.className}, ${struct.className}DTO, ${struct.className}VO> implements I${struct.className}Service<I${struct.className}DAO> {
	
}

package ${BASE_PKG}.controller${module_name};

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

<#--
import ${BASE_PKG}.${DAO}${module_name}.I${struct.className}DAO;
-->
import ${POJO_BASE_PKG}.${DTO}${module_name}.${struct.className}DTO;
import ${POJO_BASE_PKG}.${VO}${module_name}.${struct.className}VO;
import ${POJO_BASE_PKG}.model${module_name}.${struct.className};
import ${API_BASE_PKG}.service${module_name}.I${struct.className}Service;
import ${FRAMEWORK_PACKAGE}.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/${struct.variableName}" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
<#--
${struct.className}, ${struct.className}DTO, ${struct.className}VO, 
I${struct.className}DAO, 
-->
public class ${struct.className}Controller extends BaseController<${struct.className}, ${struct.className}DTO, ${struct.className}VO, I${struct.className}Service> {
	
}

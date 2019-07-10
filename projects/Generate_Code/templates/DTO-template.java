package ${POJO_BASE_PKG}.${DTO}${module_name};

import ${FRAMEWORK_PACKAGE}.base.DTO.BaseDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ${struct.className}DTO extends BaseDTO<${struct.className}DTO> {

	private static final long serialVersionUID = 1L;

	<#list struct.list_itemPK as itemPK>
	private ${itemPK.javaType} ${itemPK.name};
	</#list>
	<#list struct.list_item as item>
	private ${item.javaType} ${item.name};
	</#list>

}

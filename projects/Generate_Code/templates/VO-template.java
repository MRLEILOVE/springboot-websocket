package ${POJO_BASE_PKG}.${VO}${module_name};

import ${FRAMEWORK_PACKAGE}.base.VO.BaseVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ${struct.className}VO extends BaseVO<${struct.className}VO> {

	private static final long serialVersionUID = 1L;

	<#list struct.list_itemPK as itemPK>
	private ${itemPK.javaType} ${itemPK.name};
	</#list>
	<#list struct.list_item as item>
	private ${item.javaType} ${item.name};
	</#list>

}

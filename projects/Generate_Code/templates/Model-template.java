package ${POJO_BASE_PKG}.model${module_name};

import com.baomidou.mybatisplus.annotation.TableName;
import ${FRAMEWORK_PACKAGE}.base.model.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * ${struct.tableComment}
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value="${struct.tableName}")
public class ${struct.className} extends BaseModel<${struct.className}> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 *
	 */
	public static final class FieldNames {
		
		<#list struct.list_itemPK as itemPK>
		/**
		 * ${itemPK.comment}
		 */
		public static final String ${itemPK.upperColumnName} = "${itemPK.columnName}";
		
		</#list>
		<#list struct.list_item as item>
		/**
		 * ${item.comment}
		 */
		public static final String ${item.upperColumnName} = "${item.columnName}";
		
		</#list>
	};
	
	<#--
	// @TableField(value="id")
	-->
	<#list struct.list_itemPK as itemPK>
	/**
	 * ${itemPK.comment}
	 */
	private ${itemPK.javaType} ${itemPK.name};
	
	</#list>
	<#list struct.list_item as item>
	/**
	 * ${item.comment}
	 */
	private ${item.javaType} ${item.name};
	
	</#list>
}

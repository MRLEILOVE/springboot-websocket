/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.pojo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.core.framework.base.model.BaseModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 
 * 角色和菜单关联表
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = false) // true, because BeanUtil.copyObj .
@EqualsAndHashCode(callSuper = false)
@TableName(value="sys_role_menu")
public class SysRoleMenu extends BaseModel<SysRoleMenu> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 *
	 */
	public static final class FieldNames {
		
		/**
		 * 角色ID
		 */
		public static final String ROLE_ID = "role_id";
		
		/**
		 * 菜单ID
		 */
		public static final String MENU_ID = "menu_id";
		
	};
	
	/**
	 * 角色ID
	 */
	private Integer roleId;
	
	/**
	 * 菜单ID
	 */
	private Integer menuId;
	
}

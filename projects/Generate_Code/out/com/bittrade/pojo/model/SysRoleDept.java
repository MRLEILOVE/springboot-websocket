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
 * 角色和部门关联表
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName(value="sys_role_dept")
public class SysRoleDept extends BaseModel<SysRoleDept> {
	
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
		 * 部门ID
		 */
		public static final String DEPT_ID = "dept_id";
		
	};
	
	/**
	 * 角色ID
	 */
	private Integer roleId;
	
	/**
	 * 部门ID
	 */
	private Integer deptId;
	
}

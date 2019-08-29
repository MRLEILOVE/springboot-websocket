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
 * 
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName(value="sys_role")
public class SysRole extends BaseModel<SysRole> {
	
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
		 * 角色名称
		 */
		public static final String ROLE_NAME = "role_name";
		
		/**
		 * 角色权限字符串
		 */
		public static final String ROLE_KEY = "role_key";
		
		/**
		 * 显示顺序
		 */
		public static final String ROLE_SORT = "role_sort";
		
		/**
		 * 数据范围（1：全部数据权限 2：自定数据权限）
		 */
		public static final String DATA_SCOPE = "data_scope";
		
		/**
		 * 1:正常,0停用
		 */
		public static final String STATUS = "status";
		
		/**
		 * 1:存在 0：无效
		 */
		public static final String DEL_FLAG = "del_flag";
		
		/**
		 * 创建者
		 */
		public static final String CREATE_BY = "create_by";
		
		/**
		 * 创建时间
		 */
		public static final String CREATE_TIME = "create_time";
		
		/**
		 * 更新者
		 */
		public static final String UPDATE_BY = "update_by";
		
		/**
		 * 更新时间
		 */
		public static final String UPDATE_TIME = "update_time";
		
		/**
		 * 备注
		 */
		public static final String REMARK = "remark";
		
	};
	
	/**
	 * 角色ID
	 */
	@com.baomidou.mybatisplus.annotation.TableId(value = "role_id", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
	private Integer roleId;
	
	/**
	 * 角色名称
	 */
	private String roleName;
	
	/**
	 * 角色权限字符串
	 */
	private String roleKey;
	
	/**
	 * 显示顺序
	 */
	private Integer roleSort;
	
	/**
	 * 数据范围（1：全部数据权限 2：自定数据权限）
	 */
	private Byte dataScope;
	
	/**
	 * 1:正常,0停用
	 */
	private Byte status;
	
	/**
	 * 1:存在 0：无效
	 */
	private Byte delFlag;
	
	/**
	 * 创建者
	 */
	private String createBy;
	
	/**
	 * 创建时间
	 */
	private java.time.LocalDateTime createTime;
	
	/**
	 * 更新者
	 */
	private String updateBy;
	
	/**
	 * 更新时间
	 */
	private java.time.LocalDateTime updateTime;
	
	/**
	 * 备注
	 */
	private String remark;
	
}

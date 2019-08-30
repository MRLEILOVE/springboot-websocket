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
 * 部门表
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = false) // true, because BeanUtil.copyObj .
@EqualsAndHashCode(callSuper = false)
@TableName(value="sys_dept")
public class SysDept extends BaseModel<SysDept> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 *
	 */
	public static final class FieldNames {
		
		/**
		 * 部门id
		 */
		public static final String DEPT_ID = "dept_id";
		
		/**
		 * 父部门id
		 */
		public static final String PARENT_ID = "parent_id";
		
		/**
		 * 祖级列表
		 */
		public static final String ANCESTORS = "ancestors";
		
		/**
		 * 部门名称
		 */
		public static final String DEPT_NAME = "dept_name";
		
		/**
		 * 显示顺序
		 */
		public static final String ORDER_NUM = "order_num";
		
		/**
		 * 负责人
		 */
		public static final String LEADER = "leader";
		
		/**
		 * 联系电话
		 */
		public static final String PHONE = "phone";
		
		/**
		 * 邮箱
		 */
		public static final String EMAIL = "email";
		
		/**
		 * 部门状态（1正常 0停用）
		 */
		public static final String STATUS = "status";
		
		/**
		 * 删除标志（0代表删除 1代表正常）
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
		
	};
	
	/**
	 * 部门id
	 */
	@com.baomidou.mybatisplus.annotation.TableId(value = "dept_id", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
	private Integer deptId;
	
	/**
	 * 父部门id
	 */
	private Integer parentId;
	
	/**
	 * 祖级列表
	 */
	private String ancestors;
	
	/**
	 * 部门名称
	 */
	private String deptName;
	
	/**
	 * 显示顺序
	 */
	private Integer orderNum;
	
	/**
	 * 负责人
	 */
	private String leader;
	
	/**
	 * 联系电话
	 */
	private String phone;
	
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 部门状态（1正常 0停用）
	 */
	private String status;
	
	/**
	 * 删除标志（0代表删除 1代表正常）
	 */
	private String delFlag;
	
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
	
}

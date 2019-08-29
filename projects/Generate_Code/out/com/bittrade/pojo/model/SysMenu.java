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
 * 菜单权限表
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName(value="sys_menu")
public class SysMenu extends BaseModel<SysMenu> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 *
	 */
	public static final class FieldNames {
		
		/**
		 * 菜单ID
		 */
		public static final String MENU_ID = "menu_id";
		
		/**
		 * 菜单名称
		 */
		public static final String MENU_NAME = "menu_name";
		
		/**
		 * 父菜单ID
		 */
		public static final String PARENT_ID = "parent_id";
		
		/**
		 * 显示顺序
		 */
		public static final String ORDER_NUM = "order_num";
		
		/**
		 * 请求地址
		 */
		public static final String URL = "url";
		
		/**
		 * 菜单类型（M目录 C菜单 F按钮）
		 */
		public static final String MENU_TYPE = "menu_type";
		
		/**
		 * 菜单状态（1显示 0隐藏）
		 */
		public static final String VISIBLE = "visible";
		
		/**
		 * 权限标识
		 */
		public static final String PERMS = "perms";
		
		/**
		 * 菜单图标
		 */
		public static final String ICON = "icon";
		
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
	 * 菜单ID
	 */
	@com.baomidou.mybatisplus.annotation.TableId(value = "menu_id", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
	private Integer menuId;
	
	/**
	 * 菜单名称
	 */
	private String menuName;
	
	/**
	 * 父菜单ID
	 */
	private Integer parentId;
	
	/**
	 * 显示顺序
	 */
	private Integer orderNum;
	
	/**
	 * 请求地址
	 */
	private String url;
	
	/**
	 * 菜单类型（M目录 C菜单 F按钮）
	 */
	private String menuType;
	
	/**
	 * 菜单状态（1显示 0隐藏）
	 */
	private String visible;
	
	/**
	 * 权限标识
	 */
	private String perms;
	
	/**
	 * 菜单图标
	 */
	private String icon;
	
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

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
 * 字典数据表
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName(value="sys_dict_data")
public class SysDictData extends BaseModel<SysDictData> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 *
	 */
	public static final class FieldNames {
		
		/**
		 * 字典编码
		 */
		public static final String DICT_CODE = "dict_code";
		
		/**
		 * 字典排序
		 */
		public static final String DICT_SORT = "dict_sort";
		
		/**
		 * 字典标签
		 */
		public static final String DICT_LABEL = "dict_label";
		
		/**
		 * 字典键值
		 */
		public static final String DICT_VALUE = "dict_value";
		
		/**
		 * 字典类型
		 */
		public static final String DICT_TYPE = "dict_type";
		
		/**
		 * 样式属性（其他样式扩展）
		 */
		public static final String CSS_CLASS = "css_class";
		
		/**
		 * 表格回显样式
		 */
		public static final String LIST_CLASS = "list_class";
		
		/**
		 * 是否默认（Y是 N否）
		 */
		public static final String IS_DEFAULT = "is_default";
		
		/**
		 * 状态（1正常 停0用）
		 */
		public static final String STATUS = "status";
		
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
	 * 字典编码
	 */
	@com.baomidou.mybatisplus.annotation.TableId(value = "dictCode", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
	private Integer dictCode;
	
	/**
	 * 字典排序
	 */
	private Integer dictSort;
	
	/**
	 * 字典标签
	 */
	private String dictLabel;
	
	/**
	 * 字典键值
	 */
	private String dictValue;
	
	/**
	 * 字典类型
	 */
	private String dictType;
	
	/**
	 * 样式属性（其他样式扩展）
	 */
	private String cssClass;
	
	/**
	 * 表格回显样式
	 */
	private String listClass;
	
	/**
	 * 是否默认（Y是 N否）
	 */
	private String isDefault;
	
	/**
	 * 状态（1正常 停0用）
	 */
	private String status;
	
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

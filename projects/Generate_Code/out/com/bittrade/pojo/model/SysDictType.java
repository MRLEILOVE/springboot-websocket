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
 * 字典类型表
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName(value="sys_dict_type")
public class SysDictType extends BaseModel<SysDictType> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 *
	 */
	public static final class FieldNames {
		
		/**
		 * 字典主键
		 */
		public static final String DICT_ID = "dict_id";
		
		/**
		 * 字典名称
		 */
		public static final String DICT_NAME = "dict_name";
		
		/**
		 * 字典类型
		 */
		public static final String DICT_TYPE = "dict_type";
		
		/**
		 * 状态（1正常 0停用）
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
	 * 字典主键
	 */
	@com.baomidou.mybatisplus.annotation.TableId(value = "dictId", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
	private Integer dictId;
	
	/**
	 * 字典名称
	 */
	private String dictName;
	
	/**
	 * 字典类型
	 */
	private String dictType;
	
	/**
	 * 状态（1正常 0停用）
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

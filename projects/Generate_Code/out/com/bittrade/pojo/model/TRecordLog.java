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
 * 信息发送日志
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = false) // true, because BeanUtil.copyObj .
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_record_log")
public class TRecordLog extends BaseModel<TRecordLog> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 *
	 */
	public static final class FieldNames {
		
		/**
		 * 主键
		 */
		public static final String ID = "id";
		
		/**
		 * 设备类型：
		 */
		public static final String EQUIPMENT_TYPE = "equipment_type";
		
		/**
		 * 业务类型
		 */
		public static final String BUSINESS_TYPE = "business_type";
		
		/**
		 * 发送的地址
		 */
		public static final String EQUIPMENT_MARK = "equipment_mark";
		
		/**
		 * 消息内容
		 */
		public static final String CONTENT = "content";
		
		/**
		 * 创建时间
		 */
		public static final String CREATE_TIME = "create_time";
		
	};
	
	/**
	 * 主键
	 */
	@com.baomidou.mybatisplus.annotation.TableId(value = "id", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
	private Long id;
	
	/**
	 * 设备类型：
	 */
	private Byte equipmentType;
	
	/**
	 * 业务类型
	 */
	private String businessType;
	
	/**
	 * 发送的地址
	 */
	private String equipmentMark;
	
	/**
	 * 消息内容
	 */
	private String content;
	
	/**
	 * 创建时间
	 */
	private java.time.LocalDateTime createTime;
	
}

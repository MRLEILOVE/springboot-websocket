package com.bittrade.pojo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.core.framework.base.model.BaseModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_chat_record_log")
public class TChatRecordLog extends BaseModel<TChatRecordLog> {
	
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
		 * 发送者ID
		 */
		public static final String SENDER_ID = "sender_id";
		
		/**
		 * 接受者ID
		 */
		public static final String RECEIVER_ID = "receiver_id";
		
		/**
		 * 发送的内容
		 */
		public static final String CONTENT = "content";
		
		/**
		 * 发送的类型:0,文本,1,图片
		 */
		public static final String MESSAGE_TYPE = "message_type";
		
		/**
		 * 创建时间
		 */
		public static final String CREATE_TIME = "create_time";
		
		/**
		 * 修改时间
		 */
		public static final String UPDATE_TIME = "update_time";
		
	};
	
	/**
	 * 主键
	 */
	@com.baomidou.mybatisplus.annotation.TableId(value = "id", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
	private Long id;
	
	/**
	 * 发送者ID
	 */
	private Long senderId;
	
	/**
	 * 接受者ID
	 */
	private Long receiverId;
	
	/**
	 * 发送的内容
	 */
	private Byte[] content;
	
	/**
	 * 发送的类型:0,文本,1,图片
	 */
	private Byte messageType;
	
	/**
	 * 创建时间
	 */
	private java.time.LocalDateTime createTime;
	
	/**
	 * 修改时间
	 */
	private java.time.LocalDateTime updateTime;
	
}

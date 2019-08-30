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
 * 划转配置表
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_transfer_direction")
public class TTransferDirection extends BaseModel<TTransferDirection> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 *
	 */
	public static final class FieldNames {
		
		/**
		 * 
		 */
		public static final String ID = "id";
		
		/**
		 * 
		 */
		public static final String CREATE_TIME = "create_time";
		
		/**
		 * 
		 */
		public static final String UPDATE_TIME = "update_time";
		
		/**
		 * 主账户
		 */
		public static final String MAIN_ACCOUNT_ID = "main_account_id";
		
		/**
		 * 目标账户
		 */
		public static final String TARGET_ACCOUNT_ID = "target_account_id";
		
		/**
		 * 状态：0禁用，1启用
		 */
		public static final String STATUS = "status";
		
	};
	
	/**
	 * 
	 */
	@com.baomidou.mybatisplus.annotation.TableId(value = "id", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
	private Integer id;
	
	/**
	 * 
	 */
	private java.time.LocalDateTime createTime;
	
	/**
	 * 
	 */
	private java.time.LocalDateTime updateTime;
	
	/**
	 * 主账户
	 */
	private Integer mainAccountId;
	
	/**
	 * 目标账户
	 */
	private Integer targetAccountId;
	
	/**
	 * 状态：0禁用，1启用
	 */
	private Integer status;
	
}

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
 * 虚拟货币类型表
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_currency")
public class TCurrency extends BaseModel<TCurrency> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 *
	 */
	public static final class FieldNames {
		
		/**
		 * 主键id
		 */
		public static final String ID = "id";
		
		/**
		 * 名称
		 */
		public static final String NAME = "name";
		
		/**
		 * 简称
		 */
		public static final String SHORT_NAME = "short_name";
		
		/**
		 * 状态:0禁用,1正常
		 */
		public static final String STATUS = "status";
		
		/**
		 * 法币:0否,1是
		 */
		public static final String TYPE = "type";
		
		/**
		 * 是否允许充值 0：禁止 1：允许
		 */
		public static final String IS_RECHARGE = "is_recharge";
		
		/**
		 * 是否允许提现 0：禁止 1：允许
		 */
		public static final String IS_WITHDRAW = "is_withdraw";
		
		/**
		 * 描述
		 */
		public static final String DESC = "desc";
		
		/**
		 * 创建时间
		 */
		public static final String CREATE_TIME = "create_time";
		
	};
	
	/**
	 * 主键id
	 */
	@com.baomidou.mybatisplus.annotation.TableId(value = "id", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
	private Integer id;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 简称
	 */
	private String shortName;
	
	/**
	 * 状态:0禁用,1正常
	 */
	private Byte status;
	
	/**
	 * 法币:0否,1是
	 */
	private Byte type;
	
	/**
	 * 是否允许充值 0：禁止 1：允许
	 */
	private Byte isRecharge;
	
	/**
	 * 是否允许提现 0：禁止 1：允许
	 */
	private Byte isWithdraw;
	
	/**
	 * 描述
	 */
	private String desc;
	
	/**
	 * 创建时间
	 */
	private java.time.LocalDateTime createTime;
	
}

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
 * 用户资金账户记录
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_user_capital_account_record")
public class TUserCapitalAccountRecord extends BaseModel<TUserCapitalAccountRecord> {
	
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
		 * 用户id
		 */
		public static final String USER_ID = "user_id";
		
		/**
		 * 货币id，t_currency表中的id
		 */
		public static final String CURRENCY_ID = "currency_id";
		
		/**
		 * 数量
		 */
		public static final String COUNT = "count";
		
		/**
		 * 地址
		 */
		public static final String ADDERESS = "adderess";
		
		/**
		 * 手续费
		 */
		public static final String FEES = "fees";
		
		/**
		 * 类型:1:充币,2提币,3:撤销提现
		 */
		public static final String TYPE = "type";
		
		/**
		 * 状态:0:失败,1:成功
		 */
		public static final String STATUS = "status";
		
		/**
		 * 创建时间
		 */
		public static final String CREATE_TIME = "create_time";
		
		/**
		 * 更新时间
		 */
		public static final String UPDATE_TIME = "update_time";
		
	};
	
	/**
	 * 主键id
	 */
	private Long id;
	
	/**
	 * 用户id
	 */
	private Long userId;
	
	/**
	 * 货币id，t_currency表中的id
	 */
	private Integer currencyId;
	
	/**
	 * 数量
	 */
	private java.math.BigDecimal count;
	
	/**
	 * 地址
	 */
	private String adderess;
	
	/**
	 * 手续费
	 */
	private java.math.BigDecimal fees;
	
	/**
	 * 类型:1:充币,2提币,3:撤销提现
	 */
	private Byte type;
	
	/**
	 * 状态:0:失败,1:成功
	 */
	private Byte status;
	
	/**
	 * 创建时间
	 */
	private java.time.LocalDateTime createTime;
	
	/**
	 * 更新时间
	 */
	private java.time.LocalDateTime updateTime;
	
}

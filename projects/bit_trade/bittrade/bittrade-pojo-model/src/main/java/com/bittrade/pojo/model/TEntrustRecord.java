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
 * 委托交易日志
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_entrust_record")
public class TEntrustRecord extends BaseModel<TEntrustRecord> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 *
	 */
	public static final class FieldNames {
		
		/**
		 * 委托明细表id
		 */
		public static final String ID = "id";
		
		/**
		 * 用户id
		 */
		public static final String USER_ID = "user_id";
		
		/**
		 * 对手方用户id
		 */
		public static final String RIVAL_USER_ID = "rival_user_id";
		
		/**
		 * 委托单id,t_fentrust.id
		 */
		public static final String ENTRUST_ID = "entrust_id";
		
		/**
		 * 对手委托单id,t_fentrust.id
		 */
		public static final String RIVAL_ENTRUST_ID = "rival_entrust_id";
		
		/**
		 * 总金额
		 */
		public static final String AMOUNT = "amount";
		
		/**
		 * 价格
		 */
		public static final String PRICE = "price";
		
		/**
		 * 数量
		 */
		public static final String COUNT = "count";
		
		/**
		 * 费率
		 */
		public static final String RATE = "rate";
		
		/**
		 * 费用
		 */
		public static final String FEES = "fees";
		
		/**
		 * 交易对id,t_currency_trade中的id
		 */
		public static final String CURRENCY_TRADE_ID = "currency_trade_id";
		
		/**
		 * 是否主动:1-主买主卖，0-被买被卖
		 */
		public static final String IS_ACTIVE = "is_active";
		
		/**
		 * 交易类型:0-买，1-卖
		 */
		public static final String ENTRUST_DIRECTION = "entrust_direction";
		
		/**
		 * 创建时间
		 */
		public static final String CREATE_TIME = "create_time";
		
	};
	
	/**
	 * 委托明细表id
	 */
	private Long id;
	
	/**
	 * 用户id
	 */
	private Long userId;
	
	/**
	 * 对手方用户id
	 */
	private Long rivalUserId;
	
	/**
	 * 委托单id,t_fentrust.id
	 */
	private Long entrustId;
	
	/**
	 * 对手委托单id,t_fentrust.id
	 */
	private Long rivalEntrustId;
	
	/**
	 * 总金额
	 */
	private java.math.BigDecimal amount;
	
	/**
	 * 价格
	 */
	private java.math.BigDecimal price;
	
	/**
	 * 数量
	 */
	private java.math.BigDecimal count;
	
	/**
	 * 费率
	 */
	private java.math.BigDecimal rate;
	
	/**
	 * 费用
	 */
	private java.math.BigDecimal fees;
	
	/**
	 * 交易对id,t_currency_trade中的id
	 */
	private Integer currencyTradeId;
	
	/**
	 * 是否主动:1-主买主卖，0-被买被卖
	 */
	private Byte isActive;
	
	/**
	 * 交易类型:0-买，1-卖
	 */
	private Integer entrustDirection;
	
	/**
	 * 创建时间
	 */
	private java.time.LocalDateTime createTime;
	
}

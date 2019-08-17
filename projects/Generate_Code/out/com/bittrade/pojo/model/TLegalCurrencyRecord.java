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
 * 法币流水表
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_legal_currency_record")
public class TLegalCurrencyRecord extends BaseModel<TLegalCurrencyRecord> {
	
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
		 * 法币币种id
		 */
		public static final String COIN_ID = "coin_id";
		
		/**
		 * 成交前金额
		 */
		public static final String BEFORE_AMOUNT = "before_amount";
		
		/**
		 * 变动金额 (流入 +，流出 -)
		 */
		public static final String CHANGE_AMOUNT = "change_amount";
		
		/**
		 * 成交前金额
		 */
		public static final String AFTER_AMOUNT = "after_amount";
		
		/**
		 * 流水类型, 1:  法币买入 2：法币出售，3:法币钱包-->币币钱包 4：法币钱包-->资金钱包  5：资金钱包 --> 法币钱包  6：币币钱包 -->法币钱包
		 */
		public static final String TYPE = "type";
		
		/**
		 * 业务订单id
		 */
		public static final String BIZ_ORDER_ID = "biz_order_id";
		
		/**
		 * 创建时间
		 */
		public static final String CREATE_TIME = "create_time";
		
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
	 * 法币币种id
	 */
	private Integer coinId;
	
	/**
	 * 成交前金额
	 */
	private java.math.BigDecimal beforeAmount;
	
	/**
	 * 变动金额 (流入 +，流出 -)
	 */
	private java.math.BigDecimal changeAmount;
	
	/**
	 * 成交前金额
	 */
	private java.math.BigDecimal afterAmount;
	
	/**
	 * 流水类型, 1:  法币买入 2：法币出售，3:法币钱包-->币币钱包 4：法币钱包-->资金钱包  5：资金钱包 --> 法币钱包  6：币币钱包 -->法币钱包
	 */
	private Integer type;
	
	/**
	 * 业务订单id
	 */
	private Byte bizOrderId;
	
	/**
	 * 创建时间
	 */
	private java.time.LocalDateTime createTime;
	
}

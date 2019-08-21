package com.wallet.biz.pojo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.core.framework.base.model.BaseModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * 资金钱包流水表
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value="w_wallet_account_record")
public class WWalletAccountRecord extends BaseModel<WWalletAccountRecord> {
	
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
		 * 币种id
		 */
		public static final String CURRENCY_ID = "currency_id";
		
		/**
		 * 成交前金额
		 */
		public static final String BEFORE_AMOUNT = "before_amount";
		
		/**
		 * 成交后金额
		 */
		public static final String AFTER_AMOUNT = "after_amount";
		
		/**
		 * 变动金额 转入+  转出-
		 */
		public static final String CHANGE_AMOUNT = "change_amount";
		
		/**
		 * 成交类型,1充币 2提币 3-划转：币币钱包-->资金钱包 4-划转：资金钱包-->币币钱包 5-划转:资金钱包-->c2c钱包  6-划转:c2c钱包-->资金钱包
		 */
		public static final String TYPE = "type";
		
		/**
		 * 关联的id,订单id
		 */
		public static final String ORDER_ID = "order_id";
		
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
	 * 币种id
	 */
	private Integer currencyId;
	
	/**
	 * 成交前金额
	 */
	private java.math.BigDecimal beforeAmount;
	
	/**
	 * 成交后金额
	 */
	private java.math.BigDecimal afterAmount;
	
	/**
	 * 变动金额 转入+  转出-
	 */
	private java.math.BigDecimal changeAmount;
	
	/**
	 * 成交类型,1充币 2提币 3-划转：币币钱包-->资金钱包 4-划转：资金钱包-->币币钱包 5-划转:资金钱包-->c2c钱包  6-划转:c2c钱包-->资金钱包
	 */
	private Byte type;
	
	/**
	 * 关联的id,订单id
	 */
	private Long orderId;
	
	/**
	 * 创建时间
	 */
	private java.time.LocalDateTime createTime;
	
}

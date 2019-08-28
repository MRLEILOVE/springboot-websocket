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
 * 虚拟币钱包表日志表
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_wallet_record")
public class TWalletRecord extends BaseModel<TWalletRecord> {
	
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
		 * 货币id(t_currency.id)
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
		 * 变动金额
		 */
		public static final String CHANGE_AMOUNT = "change_amount";
		
		/**
		 * 成交类型,1-划转：币币钱包-->法币钱包 2-划转：法币钱包-->币币钱包 3-买入 4-卖出 5-划转：币币钱包-->资金钱包 6-划转：资金钱包-->币币钱包
		 */
		public static final String TYPE = "type";
		
		/**
		 * 关联的id,当前主要指成交id,t_entrust_record.id
		 */
		public static final String ENTRUST_RECORD_ID = "entrust_record_id";
		
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
	 * 货币id(t_currency.id)
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
	 * 变动金额
	 */
	private java.math.BigDecimal changeAmount;
	
	/**
	 * 成交类型,1-划转：币币钱包-->法币钱包 2-划转：法币钱包-->币币钱包 3-买入 4-卖出 5-划转：币币钱包-->资金钱包 6-划转：资金钱包-->币币钱包
	 */
	private Byte type;
	
	/**
	 * 关联的id,当前主要指成交id,t_entrust_record.id
	 */
	private Long entrustRecordId;
	
	/**
	 * 创建时间
	 */
	private java.time.LocalDateTime createTime;
	
}

package com.bittrade.pojo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.core.framework.base.model.BaseModel;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * 资金账户流水表
 * @author Administrator
 *
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_wallet_fund_account_record")
public class TWalletFundAccountRecord extends BaseModel<TWalletFundAccountRecord> {
	
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
		 * 币种
		 */
		public static final String CURRENCY = "currency";
		
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
		 * 成交类型,1-充值 2-提现 3-币币交易 4-划转：币币钱包-->法币钱包 5-划转：法币钱包-->币币钱包
		 */
		public static final String TYPE = "type";
		
		/**
		 * 创建时间
		 */
		public static final String CREATE_TIME = "create_time";
		
		/**
		 * 关联资金账户id
		 */
		public static final String FUND_ACCOUNT_ID = "fund_account_id";
		
	};
	
	/**
	 * 主键id
	 */
	@com.baomidou.mybatisplus.annotation.TableId(value = "id", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
	private Long id;
	
	/**
	 * 用户id
	 */
	private Long userId;
	
	/**
	 * 币种
	 */
	private String currency;
	
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
	 * 成交类型,1-充值 2-提现 3-币币交易 4-划转：币币钱包-->法币钱包 5-划转：法币钱包-->币币钱包
	 */
	private Byte type;
	
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	
	/**
	 * 关联资金账户id
	 */
	private Long fundAccountId;
	
}

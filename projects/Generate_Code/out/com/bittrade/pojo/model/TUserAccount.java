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
 * 
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_user_account")
public class TUserAccount extends BaseModel<TUserAccount> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 *
	 */
	public static final class FieldNames {
		
		/**
		 * ID
		 */
		public static final String ID = "id";
		
		/**
		 * 用户id
		 */
		public static final String USER_ID = "user_id";
		
		/**
		 * 用户名
		 */
		public static final String LOGIN_NAME = "login_name";
		
		/**
		 * 姓名
		 */
		public static final String REAL_NAME = "real_name";
		
		/**
		 * 证件审核：0未提交，1通过，2审核中，3审核拒绝'
		 */
		public static final String FHAS_REAL_VALIDATE = "fhas_real_Validate";
		
		/**
		 * 账户余额
		 */
		public static final String BALANCE = "balance";
		
		/**
		 * 体验金余额
		 */
		public static final String BALANCE_TOKEN = "balance_token";
		
		/**
		 * 冻结金额(提现)
		 */
		public static final String FROZEN_AMOUNT = "frozen_amount";
		
		/**
		 * 总入金(充值)
		 */
		public static final String TOTAL_DEPOSITS = "total_deposits";
		
		/**
		 * 体验金总充值
		 */
		public static final String TOTAL_RECHARGE_TOKEN = "total_recharge_token";
		
		/**
		 * 总出金(提现)
		 */
		public static final String TOTAL_WITHDRAWALS = "total_withdrawals";
		
		/**
		 * 占用保证金(下单冻结)
		 */
		public static final String USED_MARGIN = "used_margin";
		
		/**
		 * 体验金下单冻结
		 */
		public static final String USED_TOKEN = "used_token";
		
		/**
		 * 体验金剩余使用次数
		 */
		public static final String TOKEN_NUMBER = "token_number";
		
		/**
		 * 是否内部账户 1=正常账户、2=内部账户
		 */
		public static final String INTERNAL_ACCOUNT = "internal_account";
		
		/**
		 * 版本号，每更新一次数据加1
		 */
		public static final String VERSION = "version";
		
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
	 * ID
	 */
	@com.baomidou.mybatisplus.annotation.TableId(value = "id", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
	private Long id;
	
	/**
	 * 用户id
	 */
	private Long userId;
	
	/**
	 * 用户名
	 */
	private String loginName;
	
	/**
	 * 姓名
	 */
	private String realName;
	
	/**
	 * 证件审核：0未提交，1通过，2审核中，3审核拒绝'
	 */
	private Byte fhasRealValidate;
	
	/**
	 * 账户余额
	 */
	private java.math.BigDecimal balance;
	
	/**
	 * 体验金余额
	 */
	private java.math.BigDecimal balanceToken;
	
	/**
	 * 冻结金额(提现)
	 */
	private java.math.BigDecimal frozenAmount;
	
	/**
	 * 总入金(充值)
	 */
	private java.math.BigDecimal totalDeposits;
	
	/**
	 * 体验金总充值
	 */
	private java.math.BigDecimal totalRechargeToken;
	
	/**
	 * 总出金(提现)
	 */
	private java.math.BigDecimal totalWithdrawals;
	
	/**
	 * 占用保证金(下单冻结)
	 */
	private java.math.BigDecimal usedMargin;
	
	/**
	 * 体验金下单冻结
	 */
	private java.math.BigDecimal usedToken;
	
	/**
	 * 体验金剩余使用次数
	 */
	private Integer tokenNumber;
	
	/**
	 * 是否内部账户 1=正常账户、2=内部账户
	 */
	private Byte internalAccount;
	
	/**
	 * 版本号，每更新一次数据加1
	 */
	private Integer version;
	
	/**
	 * 创建时间
	 */
	private java.time.LocalDateTime createTime;
	
	/**
	 * 更新时间
	 */
	private java.time.LocalDateTime updateTime;
	
}

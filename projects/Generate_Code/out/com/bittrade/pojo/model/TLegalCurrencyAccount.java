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
 * 法币账户表
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_legal_currency_account")
public class TLegalCurrencyAccount extends BaseModel<TLegalCurrencyAccount> {
	
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
		 * 可用余额
		 */
		public static final String BALANCE_AMOUNT = "balance_amount";
		
		/**
		 * 冻结金额
		 */
		public static final String FREEZE_AMOUNT = "freeze_amount";
		
		/**
		 * c2c已成交数量
		 */
		public static final String C2C_ALREADY_DEAL_COUNT = "c2c_already_deal_count";
		
		/**
		 * c2c总成交数量
		 */
		public static final String C2C_TOTAL_COUNT = "c2c_total_count";
		
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
	 * 主键id
	 */
	@com.baomidou.mybatisplus.annotation.TableId(value = "id", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
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
	 * 可用余额
	 */
	private java.math.BigDecimal balanceAmount;
	
	/**
	 * 冻结金额
	 */
	private java.math.BigDecimal freezeAmount;
	
	/**
	 * c2c已成交数量
	 */
	private Integer c2cAlreadyDealCount;
	
	/**
	 * c2c总成交数量
	 */
	private Integer c2cTotalCount;
	
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

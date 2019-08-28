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
 * 资金钱包表
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName(value="w_wallet_account")
public class WWalletAccount extends BaseModel<WWalletAccount> {
	
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
		 * 可用总数量
		 */
		public static final String TOTAL = "total";
		
		/**
		 * 交易冻结数量
		 */
		public static final String TRADE_FROZEN = "trade_frozen";
		
		/**
		 * 划转冻结数量
		 */
		public static final String TRANSFER_FROZEN = "transfer_frozen";
		
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
	 * 可用总数量
	 */
	private java.math.BigDecimal total;
	
	/**
	 * 交易冻结数量
	 */
	private java.math.BigDecimal tradeFrozen;
	
	/**
	 * 划转冻结数量
	 */
	private java.math.BigDecimal transferFrozen;
	
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

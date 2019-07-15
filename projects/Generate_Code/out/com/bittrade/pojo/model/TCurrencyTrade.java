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
 * 法币类型匹配表
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_currency_trade")
public class TCurrencyTrade extends BaseModel<TCurrencyTrade> {
	
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
		 * 交易对,如BTC/USDT
		 */
		public static final String SYMBOL = "symbol";
		
		/**
		 * 货币id，t_currency表中的id
		 */
		public static final String CURRENCY_ID1 = "currency_id1";
		
		/**
		 * 法币id，t_currency表中的id
		 */
		public static final String CURRENCY_ID2 = "currency_id2";
		
		/**
		 * 单价小数位
		 */
		public static final String PRICE_DECIMAL_DIGITS = "price_decimal_digits";
		
		/**
		 * 数量小数位
		 */
		public static final String COUNT_DECIMAL_DIGITS = "count_decimal_digits";
		
		/**
		 * 最小挂单单价
		 */
		public static final String MIN_BUY_PRICE = "min_buy_price";
		
		/**
		 * 最小挂单数量
		 */
		public static final String MIN_BUY_COUNT = "min_buy_count";
		
		/**
		 * 最小挂单金额
		 */
		public static final String MIN_BUY_AMOUNT = "min_buy_amount";
		
		/**
		 * 最大可买单价
		 */
		public static final String MAX_BUY_PRICE = "max_buy_price";
		
		/**
		 * 最大可买数量
		 */
		public static final String MAX_BUY_COUNT = "max_buy_count";
		
		/**
		 * 最大可买金额
		 */
		public static final String MAX_BUY_AMOUNT = "max_buy_amount";
		
		/**
		 * 状态：0禁用，1启用
		 */
		public static final String STATUS = "status";
		
		/**
		 * 排序
		 */
		public static final String SORT = "sort";
		
		/**
		 * 创建时间
		 */
		public static final String CREATE_TIME = "create_time";
		
		/**
		 * 修改时间
		 */
		public static final String UPDATE_TIME = "update_time";
		
	};
	
	/**
	 * 主键id
	 */
	@com.baomidou.mybatisplus.annotation.TableId(value = "id", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
	private Integer id;
	
	/**
	 * 交易对,如BTC/USDT
	 */
	private String symbol;
	
	/**
	 * 货币id，t_currency表中的id
	 */
	private Integer currencyId1;
	
	/**
	 * 法币id，t_currency表中的id
	 */
	private Integer currencyId2;
	
	/**
	 * 单价小数位
	 */
	private Integer priceDecimalDigits;
	
	/**
	 * 数量小数位
	 */
	private Integer countDecimalDigits;
	
	/**
	 * 最小挂单单价
	 */
	private java.math.BigDecimal minBuyPrice;
	
	/**
	 * 最小挂单数量
	 */
	private java.math.BigDecimal minBuyCount;
	
	/**
	 * 最小挂单金额
	 */
	private java.math.BigDecimal minBuyAmount;
	
	/**
	 * 最大可买单价
	 */
	private java.math.BigDecimal maxBuyPrice;
	
	/**
	 * 最大可买数量
	 */
	private java.math.BigDecimal maxBuyCount;
	
	/**
	 * 最大可买金额
	 */
	private java.math.BigDecimal maxBuyAmount;
	
	/**
	 * 状态：0禁用，1启用
	 */
	private Byte status;
	
	/**
	 * 排序
	 */
	private Integer sort;
	
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	
	/**
	 * 修改时间
	 */
	private java.util.Date updateTime;
	
}

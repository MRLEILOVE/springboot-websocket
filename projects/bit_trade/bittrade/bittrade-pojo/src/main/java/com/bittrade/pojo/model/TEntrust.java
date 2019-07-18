package com.bittrade.pojo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.core.framework.base.model.BaseModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * 委托交易表
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_entrust")
@ApiModel(value = "委托表")
public class TEntrust extends BaseModel<TEntrust> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 *
	 */
	public static final class FieldNames {
		
		/**
		 * 委托表ID
		 */
		@ApiModelProperty(value = "id")
		public static final String ID = "id";
		
		/**
		 * 用户id
		 */
		public static final String USER_ID = "user_id";
		
		/**
		 * 交易对id,t_currency_trade中的id
		 */
		public static final String CURRENCY_TRADE_ID = "currency_trade_id";
		
		/**
		 * 价格
		 */
		public static final String PRICE = "price";
		
		/**
		 * 金额
		 */
		public static final String AMOUNT = "amount";
		
		/**
		 * 成功撮合金额
		 */
		public static final String SUCCESS_AMOUNT = "success_amount";
		
		/**
		 * 委托数量
		 */
		public static final String COUNT = "count";
		
		/**
		 * 未成交数量
		 */
		public static final String LEFT_COUNT = "left_count";
		
		/**
		 * 总手续费
		 */
		public static final String FEES = "fees";
		
		/**
		 * 剩余的手续费(未成交完全时)
		 */
		public static final String LEFT_FEES = "left_fees";
		
		/**
		 * 状态:1未完成,2部分成交,3完全成交,4用户撤销
		 */
		public static final String STATUS = "status";
		
		/**
		 * 委托类型:0市价交易,1限价交易
		 */
		public static final String ENTRUST_TYPE = "entrust_type";
		
		/**
		 * 委托方向:0买入,1卖出
		 */
		public static final String ENTRUST_DIRECTION = "entrust_direction";
		
		/**
		 * 版本号，每更新一次数据加1
		 */
		public static final String VERSION = "version";
		
		/**
		 * 创建日期
		 */
		public static final String CREATE_TIME = "create_time";
		
		/**
		 * 更新时间
		 */
		public static final String UPDATE_TIME = "update_time";
		
	};
	
	/**
	 * 委托表ID
	 */
	private Long id;
	
	/**
	 * 用户id
	 */
	private Long userId;
	
	/**
	 * 交易对id,t_currency_trade中的id
	 */
	private Integer currencyTradeId;
	
	/**
	 * 价格
	 */
	private java.math.BigDecimal price;
	
	/**
	 * 金额
	 */
	private java.math.BigDecimal amount;
	
	/**
	 * 成功撮合金额
	 */
	private java.math.BigDecimal successAmount;
	
	/**
	 * 委托数量
	 */
	private java.math.BigDecimal count;
	
	/**
	 * 未成交数量
	 */
	private java.math.BigDecimal leftCount;
	
	/**
	 * 总手续费
	 */
	private java.math.BigDecimal fees;
	
	/**
	 * 剩余的手续费(未成交完全时)
	 */
	private java.math.BigDecimal leftFees;
	
	/**
	 * 状态:1未完成,2部分成交,3完全成交,4用户撤销
	 */
	private Integer status;
	
	/**
	 * 委托类型:0市价交易,1限价交易
	 */
	private Integer entrustType;
	
	/**
	 * 委托方向:0买入,1卖出
	 */
	private Integer entrustDirection;
	
	/**
	 * 版本号，每更新一次数据加1
	 */
	private Integer version;
	
	/**
	 * 创建日期
	 */
	private java.util.Date createTime;
	
	/**
	 * 更新时间
	 */
	private java.util.Date updateTime;
	
}

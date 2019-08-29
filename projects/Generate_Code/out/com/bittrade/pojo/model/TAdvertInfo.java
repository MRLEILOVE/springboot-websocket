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
@TableName(value="t_advert_info")
public class TAdvertInfo extends BaseModel<TAdvertInfo> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 *
	 */
	public static final class FieldNames {
		
		/**
		 * 主键
		 */
		public static final String ID = "id";
		
		/**
		 * 用户id
		 */
		public static final String USER_ID = "user_id";
		
		/**
		 * 法币id
		 */
		public static final String COIN_ID = "coin_id";
		
		/**
		 * 类型(1:出售 2:购买)
		 */
		public static final String TYPE = "type";
		
		/**
		 * 定价方式 1：固定价格 2：浮动价格
		 */
		public static final String PRICING_MODE = "pricing_mode";
		
		/**
		 * 浮动比例 (小数 0.01 = 1%)
		 */
		public static final String FLOATING_RATIO = "floating_ratio";
		
		/**
		 * 单价（CNY）
		 */
		public static final String PRICE = "price";
		
		/**
		 * 隐藏价格
		 */
		public static final String HIDE_PRICE = "hide_price";
		
		/**
		 * 最小限额（CNY）
		 */
		public static final String MIN_LIMIT = "min_limit";
		
		/**
		 * 最大限额（CNY）
		 */
		public static final String MAX_LIMIT = "max_limit";
		
		/**
		 * 广告已交易数量
		 */
		public static final String ALREADY_TRANSACTION_AMOUNT = "already_transaction_amount";
		
		/**
		 * 广告剩余数量
		 */
		public static final String BALANCE_AMOUNT = "balance_amount";
		
		/**
		 * 广告进行中冻结数量
		 */
		public static final String FREEZE_AMOUNT = "freeze_amount";
		
		/**
		 * 收款方式id，出售单为收款方式, 购买单为付款方式,多个以逗号分隔
		 */
		public static final String PAYMENT_METHOD_ID = "payment_method_id";
		
		/**
		 * 状态：1，进行中；2，已下架(暂停)；3，已撤销；
		 */
		public static final String STATUS = "status";
		
		/**
		 * 是否开启对手限制 (0 禁用 1 启用)
		 */
		public static final String OPEN_OPPONENT_LIMIT = "open_opponent_limit";
		
		/**
		 * 对手限制-认证等级
		 */
		public static final String CERTIFICATION_LEVEL = "certification_level";
		
		/**
		 * 对手限制-注册时间
		 */
		public static final String REGISTERED_TIME = "registered_time";
		
		/**
		 * 对手限制-付款时间
		 */
		public static final String PAYMENT_TIME = "payment_time";
		
		/**
		 * 交易说明（留言）
		 */
		public static final String MESSAGE = "message";
		
		/**
		 * 版本号
		 */
		public static final String VERSION = "version";
		
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
	 * 主键
	 */
	@com.baomidou.mybatisplus.annotation.TableId(value = "id", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
	private Long id;
	
	/**
	 * 用户id
	 */
	private Long userId;
	
	/**
	 * 法币id
	 */
	private Long coinId;
	
	/**
	 * 类型(1:出售 2:购买)
	 */
	private Byte type;
	
	/**
	 * 定价方式 1：固定价格 2：浮动价格
	 */
	private Byte pricingMode;
	
	/**
	 * 浮动比例 (小数 0.01 = 1%)
	 */
	private java.math.BigDecimal floatingRatio;
	
	/**
	 * 单价（CNY）
	 */
	private java.math.BigDecimal price;
	
	/**
	 * 隐藏价格
	 */
	private java.math.BigDecimal hidePrice;
	
	/**
	 * 最小限额（CNY）
	 */
	private java.math.BigDecimal minLimit;
	
	/**
	 * 最大限额（CNY）
	 */
	private java.math.BigDecimal maxLimit;
	
	/**
	 * 广告已交易数量
	 */
	private java.math.BigDecimal alreadyTransactionAmount;
	
	/**
	 * 广告剩余数量
	 */
	private java.math.BigDecimal balanceAmount;
	
	/**
	 * 广告进行中冻结数量
	 */
	private java.math.BigDecimal freezeAmount;
	
	/**
	 * 收款方式id，出售单为收款方式, 购买单为付款方式,多个以逗号分隔
	 */
	private String paymentMethodId;
	
	/**
	 * 状态：1，进行中；2，已下架(暂停)；3，已撤销；
	 */
	private Byte status;
	
	/**
	 * 是否开启对手限制 (0 禁用 1 启用)
	 */
	private Byte openOpponentLimit;
	
	/**
	 * 对手限制-认证等级
	 */
	private Byte certificationLevel;
	
	/**
	 * 对手限制-注册时间
	 */
	private java.time.LocalDateTime registeredTime;
	
	/**
	 * 对手限制-付款时间
	 */
	private Integer paymentTime;
	
	/**
	 * 交易说明（留言）
	 */
	private String message;
	
	/**
	 * 版本号
	 */
	private Long version;
	
	/**
	 * 创建时间
	 */
	private java.time.LocalDateTime createTime;
	
	/**
	 * 修改时间
	 */
	private java.time.LocalDateTime updateTime;
	
}

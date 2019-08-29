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
@TableName(value="t_advert_order")
public class TAdvertOrder extends BaseModel<TAdvertOrder> {
	
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
		 * 广告信息id
		 */
		public static final String ADVERT_ID = "advert_id";
		
		/**
		 * 法币币种id
		 */
		public static final String COIN_ID = "coin_id";
		
		/**
		 * 订单号
		 */
		public static final String ORDER_NUMBER = "order_number";
		
		/**
		 * 付款法币(CNY)
		 */
		public static final String PAYMENT_LEGAL_CURRENCY = "payment_legal_currency";
		
		/**
		 * 广告类别 1出售 2购买
		 */
		public static final String ADVERT_TYPE = "advert_type";
		
		/**
		 * 广告留言
		 */
		public static final String ADVERT_MESSAGE = "advert_message";
		
		/**
		 * 购买者ID
		 */
		public static final String BUYER_ID = "buyer_id";
		
		/**
		 * 出售者ID
		 */
		public static final String SELLER_ID = "seller_id";
		
		/**
		 * 发布者ID
		 */
		public static final String PUBLISHER_ID = "publisher_id";
		
		/**
		 * 取消者ID,系统超时取消填0（仅取消状态需填）
		 */
		public static final String CANCELLER_ID = "canceller_id";
		
		/**
		 * 交易金额(CNY)
		 */
		public static final String TRANSACTION_AMOUT = "transaction_amout";
		
		/**
		 * 交易数量
		 */
		public static final String TRANSACTION_NUM = "transaction_num";
		
		/**
		 * 交易价格(CNY)
		 */
		public static final String TRANSACTION_PRICE = "transaction_price";
		
		/**
		 * 续费率(小数 0.01 = 1%)
		 */
		public static final String RATE = "rate";
		
		/**
		 * 手续费
		 */
		public static final String CHARGE = "charge";
		
		/**
		 * 状态（0,未操作; 1，已拍下；2，已付款；3，已收款；5，已完成；6，已取消，7，超时关闭）
		 */
		public static final String STATUS = "status";
		
		/**
		 * 取消订单截止时间（默认为 点击 购买/出售 后 3 分钟）
		 */
		public static final String CANCEL_ORDER_DEADLINE = "cancel_order_deadline";
		
		/**
		 * 仲裁状态：0，未仲裁；1，已仲裁；
		 */
		public static final String ARBIT_STATUS = "arbit_status";
		
		/**
		 * 仲裁结果
		 */
		public static final String ARBIT_RESULT = "arbit_result";
		
		/**
		 * 付款时间
		 */
		public static final String PAYMENT_TIME = "payment_time";
		
		/**
		 * 放币时间
		 */
		public static final String GRANT_COIN_TIME = "grant_coin_time";
		
		/**
		 * 过期时间
		 */
		public static final String OVERDUE_TIME = "overdue_time";
		
		/**
		 * 备注
		 */
		public static final String REMARK = "remark";
		
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
	 * 广告信息id
	 */
	private Long advertId;
	
	/**
	 * 法币币种id
	 */
	private Long coinId;
	
	/**
	 * 订单号
	 */
	private String orderNumber;
	
	/**
	 * 付款法币(CNY)
	 */
	private String paymentLegalCurrency;
	
	/**
	 * 广告类别 1出售 2购买
	 */
	private Byte advertType;
	
	/**
	 * 广告留言
	 */
	private String advertMessage;
	
	/**
	 * 购买者ID
	 */
	private Long buyerId;
	
	/**
	 * 出售者ID
	 */
	private Long sellerId;
	
	/**
	 * 发布者ID
	 */
	private Long publisherId;
	
	/**
	 * 取消者ID,系统超时取消填0（仅取消状态需填）
	 */
	private Long cancellerId;
	
	/**
	 * 交易金额(CNY)
	 */
	private java.math.BigDecimal transactionAmout;
	
	/**
	 * 交易数量
	 */
	private java.math.BigDecimal transactionNum;
	
	/**
	 * 交易价格(CNY)
	 */
	private java.math.BigDecimal transactionPrice;
	
	/**
	 * 续费率(小数 0.01 = 1%)
	 */
	private java.math.BigDecimal rate;
	
	/**
	 * 手续费
	 */
	private java.math.BigDecimal charge;
	
	/**
	 * 状态（0,未操作; 1，已拍下；2，已付款；3，已收款；5，已完成；6，已取消，7，超时关闭）
	 */
	private Byte status;
	
	/**
	 * 取消订单截止时间（默认为 点击 购买/出售 后 3 分钟）
	 */
	private java.time.LocalDateTime cancelOrderDeadline;
	
	/**
	 * 仲裁状态：0，未仲裁；1，已仲裁；
	 */
	private Byte arbitStatus;
	
	/**
	 * 仲裁结果
	 */
	private String arbitResult;
	
	/**
	 * 付款时间
	 */
	private java.time.LocalDateTime paymentTime;
	
	/**
	 * 放币时间
	 */
	private java.time.LocalDateTime grantCoinTime;
	
	/**
	 * 过期时间
	 */
	private java.time.LocalDateTime overdueTime;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 创建时间
	 */
	private java.time.LocalDateTime createTime;
	
	/**
	 * 修改时间
	 */
	private java.time.LocalDateTime updateTime;
	
}

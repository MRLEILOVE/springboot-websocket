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
 * 用户订单
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value="w_order")
public class WOrder extends BaseModel<WOrder> {
	
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
		 * 用户ID
		 */
		public static final String USER_ID = "user_id";
		
		/**
		 * 订单号
		 */
		public static final String ORDER_ID = "order_id";
		
		/**
		 * 订单类型：1充币，-1提币
		 */
		public static final String ORDER_TYPE = "order_type";
		
		/**
		 * 手续费
		 */
		public static final String FEE = "fee";
		
		/**
		 * 币种大类:BTC系列、BTC_TOKEN、EOS系列、ETH、ETH_TOKEN
		 */
		public static final String COIN_TYPE = "coin_type";
		
		/**
		 * 币种  BTC、USDT、EOS、ETH等
		 */
		public static final String TOKEN = "token";
		
		/**
		 * 合约地址USDT=31
		 */
		public static final String CONTRACT = "contract";
		
		/**
		 * 数量
		 */
		public static final String AMOUNT = "amount";
		
		/**
		 * 接收地址
		 */
		public static final String RECEIVER_ADDRESS = "receiver_address";
		
		/**
		 * 订单状态：1未审核，2客服已审核，3财务已审核，4财务拒绝，5成功，6等待确认，-1客服已拒绝，-2用户取消
		 */
		public static final String TYPE = "type";
		
		/**
		 * 操作人
		 */
		public static final String OPERATOR = "operator";
		
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
	 * 用户ID
	 */
	private Long userId;
	
	/**
	 * 订单号
	 */
	private String orderId;
	
	/**
	 * 订单类型：1充币，-1提币
	 */
	private Integer orderType;
	
	/**
	 * 手续费
	 */
	private java.math.BigDecimal fee;
	
	/**
	 * 币种大类:BTC系列、BTC_TOKEN、EOS系列、ETH、ETH_TOKEN
	 */
	private String coinType;
	
	/**
	 * 币种  BTC、USDT、EOS、ETH等
	 */
	private String token;
	
	/**
	 * 合约地址USDT=31
	 */
	private String contract;
	
	/**
	 * 数量
	 */
	private java.math.BigDecimal amount;
	
	/**
	 * 接收地址
	 */
	private String receiverAddress;
	
	/**
	 * 订单状态：1未审核，2客服已审核，3财务已审核，4财务拒绝，5成功，6等待确认，-1客服已拒绝，-2用户取消
	 */
	private Integer type;
	
	/**
	 * 操作人
	 */
	private String operator;
	
	/**
	 * 创建时间
	 */
	private java.time.LocalDateTime createTime;
	
	/**
	 * 更新时间
	 */
	private java.time.LocalDateTime updateTime;
	
}

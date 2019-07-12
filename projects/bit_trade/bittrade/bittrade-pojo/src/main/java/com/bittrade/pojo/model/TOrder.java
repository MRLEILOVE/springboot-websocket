package com.bittrade.pojo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.core.framework.base.model.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * 
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_order")
public class TOrder extends BaseModel<TOrder> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 *
	 */
	public static final class FieldNames {
		
		/**
		 * 
		 */
		public static final String ID = "id";
		
		/**
		 * 提币用户
		 */
		public static final String USERID = "userId";
		
		/**
		 * 订单id
		 */
		public static final String ORDER_ID = "order_id";
		
		/**
		 * 手续费
		 */
		public static final String FEE = "fee";
		
		/**
		 * 具体币种
		 */
		public static final String TOKEN = "token";
		
		/**
		 * 提币数量
		 */
		public static final String AMOUNT = "amount";
		
		/**
		 * 接收地址
		 */
		public static final String RECEIVER_ADDRESS = "receiver_address";
		
		/**
		 * 审核状态
		 */
		public static final String TYPE = "type";
		
		/**
		 * 操作人
		 */
		public static final String OPERATOR = "operator";
		
		/**
		 * 
		 */
		public static final String CREATE_TIME = "create_time";
		
		/**
		 * 
		 */
		public static final String UPDATE_TIME = "update_time";
		
	};
	
	/**
	 * 
	 */
	private Integer id;
	
	/**
	 * 提币用户
	 */
	private Integer userId;
	
	/**
	 * 订单id
	 */
	private String orderId;
	
	/**
	 * 手续费
	 */
	private java.math.BigDecimal fee;
	
	/**
	 * 具体币种
	 */
	private String token;
	
	/**
	 * 提币数量
	 */
	private java.math.BigDecimal amount;
	
	/**
	 * 接收地址
	 */
	private String receiverAddress;
	
	/**
	 * 审核状态
	 */
	private String type;
	
	/**
	 * 操作人
	 */
	private String operator;
	
	/**
	 * 
	 */
	private java.util.Date createTime;
	
	/**
	 * 
	 */
	private java.util.Date updateTime;
	
}

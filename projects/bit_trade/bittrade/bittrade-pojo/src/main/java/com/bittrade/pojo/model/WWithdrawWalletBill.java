package com.bittrade.pojo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.core.framework.base.model.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * 提币钱包账单
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value="w_withdraw_wallet_bill")
public class WWithdrawWalletBill extends BaseModel<WWithdrawWalletBill> {
	
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
		 * 默认角色
		 */
		public static final String PLATFORM = "platform";
		
		/**
		 * 订单号
		 */
		public static final String ORDER_ID = "order_id";
		
		/**
		 * 1:进账，-1出账
		 */
		public static final String DIRECTION = "direction";
		
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
		 * 发送地址
		 */
		public static final String SENDER_ADDRESS = "sender_address";
		
		/**
		 * 接收者地址
		 */
		public static final String RECEIVER_ADDRESS = "receiver_address";
		
		/**
		 * 对应coin转账数量
		 */
		public static final String AMOUNT = "amount";
		
		/**
		 * 打包区块
		 */
		public static final String BLOCK = "block";
		
		/**
		 * 交易hash
		 */
		public static final String TX = "tx";
		
		/**
		 * 交易步骤： 10待审核 20待转账 25在转账 30已发布 40确认中 50确认完成
		 */
		public static final String TRADE_STEP = "trade_step";
		
		/**
		 * 操作类型
		 */
		public static final String OPERATION_TYPE = "operation_type";
		
		/**
		 * 转移状态：0：初始化，1：待归集，2已转移，3不用处理
		 */
		public static final String TRANSFER_FLAG = "transfer_flag";
		
		/**
		 * 0：初始化，1：未同步到资金账户，2已同步到资金账户，3不用处理
		 */
		public static final String FLAG = "flag";
		
		/**
		 * 备注
		 */
		public static final String REMARK = "remark";
		
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
	private long id;
	
	/**
	 * 用户ID
	 */
	private long userId;
	
	/**
	 * 默认角色
	 */
	private String platform;
	
	/**
	 * 订单号
	 */
	private String orderId;
	
	/**
	 * 1:进账，-1出账
	 */
	private int direction;
	
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
	 * 发送地址
	 */
	private String senderAddress;
	
	/**
	 * 接收者地址
	 */
	private String receiverAddress;
	
	/**
	 * 对应coin转账数量
	 */
	private java.math.BigDecimal amount;
	
	/**
	 * 打包区块
	 */
	private long block;
	
	/**
	 * 交易hash
	 */
	private String tx;
	
	/**
	 * 交易步骤： 10待审核 20待转账 25在转账 30已发布 40确认中 50确认完成
	 */
	private String tradeStep;
	
	/**
	 * 操作类型
	 */
	private String operationType;
	
	/**
	 * 转移状态：0：初始化，1：待归集，2已转移，3不用处理
	 */
	private byte transferFlag;
	
	/**
	 * 0：初始化，1：未同步到资金账户，2已同步到资金账户，3不用处理
	 */
	private byte flag;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	
	/**
	 * 更新时间
	 */
	private java.util.Date updateTime;
	
}

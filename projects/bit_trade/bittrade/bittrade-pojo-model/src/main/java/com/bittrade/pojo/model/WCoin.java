package com.bittrade.pojo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.core.framework.base.model.BaseModel;
import lombok.*;

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
@EqualsAndHashCode(callSuper = false)
@TableName(value="w_coin")
public class WCoin extends BaseModel<WCoin> {
	
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
		 * 是否允许充值 禁用D  启用E
		 */
		public static final String IS_RECHARGE = "is_recharge";

		/**
		 * 是否允许提现 禁用D  启用E
		 */
		public static final String IS_WITHDRAW = "is_withdraw";

		public static final String STATUS = "status";
		
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
	@com.baomidou.mybatisplus.annotation.TableId(value = "id", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
	private Long id;
	
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
	 * 是否允许充值 禁用D  启用E
	 */
	private String isRecharge;

	/**
	 * 是否允许提现 禁用D  启用E
	 */
	private String isWithdraw;

	private Byte status;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 创建时间
	 */
	private java.time.LocalDateTime createTime;
	
	/**
	 * 更新时间
	 */
	private java.time.LocalDateTime updateTime;
	
}

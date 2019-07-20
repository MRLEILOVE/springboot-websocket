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
 * 钱包划转记录表
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_wallet_transfer")
public class TWalletTransfer extends BaseModel<TWalletTransfer> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 *
	 */
	public static final class FieldNames {
		
		/**
		 * id
		 */
		public static final String ID = "id";
		
		/**
		 * 用户id
		 */
		public static final String USER_ID = "user_id";
		
		/**
		 * 货币id(t_currency的id)
		 */
		public static final String CURRENCY = "currency";
		
		/**
		 * 数量
		 */
		public static final String COUNT = "count";
		
		/**
		 * 业务流水号
		 */
		public static final String BUSINESS_NUMBER = "business_number";
		
		/**
		 * 状态，1.待处理，2.成功，3.失败，4.状态未明
		 */
		public static final String STATUS = "status";
		
		/**
		 * 操作渠道：1.C2C钱包划转到币币钱包，2.币币钱包划转到C2C钱包
		 */
		public static final String TYPECHANNEL = "typeChannel";
		
		/**
		 * 来源渠道：1.app，2.后台，3.PC
		 */
		public static final String SOURCECHANNEL = "sourceChannel";
		
		/**
		 * 描述
		 */
		public static final String DESC = "desc";
		
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
	 * id
	 */
	@com.baomidou.mybatisplus.annotation.TableId(value = "id", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
	private Long id;
	
	/**
	 * 用户id
	 */
	private Long userId;
	
	/**
	 * 货币id(t_currency的id)
	 */
	private Integer currency;
	
	/**
	 * 数量
	 */
	private java.math.BigDecimal count;
	
	/**
	 * 业务流水号
	 */
	private String businessNumber;
	
	/**
	 * 状态，1.待处理，2.成功，3.失败，4.状态未明
	 */
	private Byte status;
	
	/**
	 * 操作渠道：1.C2C钱包划转到币币钱包，2.币币钱包划转到C2C钱包
	 */
	private Byte typeChannel;
	
	/**
	 * 来源渠道：1.app，2.后台，3.PC
	 */
	private Byte sourceChannel;
	
	/**
	 * 描述
	 */
	private String desc;
	
	/**
	 * 创建时间
	 */
	private java.time.LocalDateTime createTime;
	
	/**
	 * 更新时间
	 */
	private java.time.LocalDateTime updateTime;
	
}

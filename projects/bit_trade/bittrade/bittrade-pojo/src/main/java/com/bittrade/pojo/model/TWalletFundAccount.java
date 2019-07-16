package com.bittrade.pojo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.core.framework.base.model.BaseModel;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * 资金账户表
 * @author Administrator
 *
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_wallet_fund_account")
public class TWalletFundAccount extends BaseModel<TWalletFundAccount> {
	
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
		 * 币种
		 */
		public static final String CURRENCY = "currency";
		
		/**
		 * 可用总数量
		 */
		public static final String TOTAL = "total";
		
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
	@com.baomidou.mybatisplus.annotation.TableId(value = "id", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
	private Long id;
	
	/**
	 * 用户id
	 */
	private Long userId;
	
	/**
	 * 币种
	 */
	private String currency;
	
	/**
	 * 可用总数量
	 */
	private java.math.BigDecimal total;
	
	/**
	 * 划转冻结数量
	 */
	private String transferFrozen;
	
	/**
	 * 版本号，每更新一次数据加1
	 */
	private Integer version;
	
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	
	/**
	 * 更新时间
	 */
	private java.util.Date updateTime;
	
}

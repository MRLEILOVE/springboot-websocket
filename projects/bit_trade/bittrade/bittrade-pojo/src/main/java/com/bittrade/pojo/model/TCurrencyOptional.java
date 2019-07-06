package com.bittrade.pojo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.core.framework.base.model.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * 用户的交易对自选表
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_currency_optional")
public class TCurrencyOptional extends BaseModel<TCurrencyOptional> {
	
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
		 * 交易对id,t_currency_trade中的id
		 */
		public static final String CURRENCY_TRADE_ID = "currency_trade_id";
		
		/**
		 * 状态：0删除，1启用
		 */
		public static final String STATUS = "status";
		
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
	private int id;
	
	/**
	 * 用户id
	 */
	private int userId;
	
	/**
	 * 交易对id,t_currency_trade中的id
	 */
	private int currencyTradeId;
	
	/**
	 * 状态：0删除，1启用
	 */
	private byte status;
	
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	
	/**
	 * 修改时间
	 */
	private java.util.Date updateTime;
	
}

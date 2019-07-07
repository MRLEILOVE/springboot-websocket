package com.bittrade.pojo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.core.framework.base.model.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * 用户钱包地址表
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_currency_address")
public class TCurrencyAddress extends BaseModel<TCurrencyAddress> {
	
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
		 * 货币id，t_currency表中的id
		 */
		public static final String CURRENCY_ID = "currency_id";
		
		/**
		 * 钱包地址
		 */
		public static final String ADDERESS = "adderess";
		
		/**
		 * 地址备注
		 */
		public static final String ADDRESS_REMARK = "address_remark";
		
		/**
		 * 创建日期
		 */
		public static final String CREATE_TIME = "create_time";
		
	};
	
	/**
	 * 主键id
	 */
	private int id;
	
	/**
	 * 用户id
	 */
	private long userId;
	
	/**
	 * 货币id，t_currency表中的id
	 */
	private int currencyId;
	
	/**
	 * 钱包地址
	 */
	private String adderess;
	
	/**
	 * 地址备注
	 */
	private String addressRemark;
	
	/**
	 * 创建日期
	 */
	private java.util.Date createTime;
	
}

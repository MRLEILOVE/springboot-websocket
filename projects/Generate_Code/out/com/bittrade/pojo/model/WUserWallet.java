package com.bittrade.pojo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.core.framework.base.model.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * 钱包
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value="w_user_wallet")
public class WUserWallet extends BaseModel<WUserWallet> {
	
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
		 * 默认角色
		 */
		public static final String PLATFORM = "platform";
		
		/**
		 * 用户ID
		 */
		public static final String USER_ID = "user_id";
		
		/**
		 * 币种大类
		 */
		public static final String COIN_TYPE = "coin_type";
		
		/**
		 * 地址
		 */
		public static final String ADDRESS = "address";
		
		/**
		 * 私钥
		 */
		public static final String PRIVATE_KEY = "private_key";
		
		/**
		 * 是否需要处理  0:否   1是
		 */
		public static final String FLAG = "flag";
		
		/**
		 * 是否有效：D无效E有效
		 */
		public static final String VALID = "valid";
		
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
	 * 默认角色
	 */
	private String platform;
	
	/**
	 * 用户ID
	 */
	private long userId;
	
	/**
	 * 币种大类
	 */
	private String coinType;
	
	/**
	 * 地址
	 */
	private String address;
	
	/**
	 * 私钥
	 */
	private String privateKey;
	
	/**
	 * 是否需要处理  0:否   1是
	 */
	private byte flag;
	
	/**
	 * 是否有效：D无效E有效
	 */
	private String valid;
	
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	
	/**
	 * 更新时间
	 */
	private java.util.Date updateTime;
	
}

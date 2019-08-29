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
 * 钱包
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
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
		 * 二维码
		 */
		public static final String CODE_QR = "code_qr";
		
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
	@com.baomidou.mybatisplus.annotation.TableId(value = "id", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
	private Long id;
	
	/**
	 * 默认角色
	 */
	private String platform;
	
	/**
	 * 用户ID
	 */
	private Long userId;
	
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
	 * 二维码
	 */
	private String codeQr;
	
	/**
	 * 是否需要处理  0:否   1是
	 */
	private Byte flag;
	
	/**
	 * 是否有效：D无效E有效
	 */
	private String valid;
	
	/**
	 * 创建时间
	 */
	private java.time.LocalDateTime createTime;
	
	/**
	 * 更新时间
	 */
	private java.time.LocalDateTime updateTime;
	
}

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
 * 
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value="w_wallet_address")
public class WWalletAddress extends BaseModel<WWalletAddress> {
	
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
		 * 
		 */
		public static final String USER_ID = "user_id";
		
		/**
		 * 名称
		 */
		public static final String NAME = "name";
		
		/**
		 * 类型
		 */
		public static final String TOKEN_TYPE = "token_type";
		
		/**
		 * 货币id(t_currency.id)
		 */
		public static final String CURRENCY_ID = "currency_id";
		
		/**
		 * 地址
		 */
		public static final String ADDRESS = "address";
		
		/**
		 * 状态:0禁用,1正常
		 */
		public static final String STATUS = "status";
		
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
	@com.baomidou.mybatisplus.annotation.TableId(value = "id", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
	private Integer id;
	
	/**
	 * 
	 */
	private Long userId;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 类型
	 */
	private String tokenType;
	
	/**
	 * 货币id(t_currency.id)
	 */
	private Integer currencyId;
	
	/**
	 * 地址
	 */
	private String address;
	
	/**
	 * 状态:0禁用,1正常
	 */
	private Byte status;
	
	/**
	 * 
	 */
	private java.time.LocalDateTime createTime;
	
	/**
	 * 
	 */
	private java.time.LocalDateTime updateTime;
	
}

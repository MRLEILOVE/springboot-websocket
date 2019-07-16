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
 * 配置钱包
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value="w_config_wallet")
public class WConfigWallet extends BaseModel<WConfigWallet> {
	
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
		 * 币种大类
		 */
		public static final String COIN_TYPE = "coin_type";
		
		/**
		 * 钱包类型:withdarw[提币地址]fee[手续费地址]collect[归集]
		 */
		public static final String WALLET_TYPE = "wallet_type";
		
		/**
		 * 地址
		 */
		public static final String ADDRESS = "address";
		
		/**
		 * keystore
		 */
		public static final String KEYSTORE = "keystore";
		
		/**
		 * 是否有效：D无效E有效
		 */
		public static final String VALID = "valid";
		
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
	 * 币种大类
	 */
	private String coinType;
	
	/**
	 * 钱包类型:withdarw[提币地址]fee[手续费地址]collect[归集]
	 */
	private String walletType;
	
	/**
	 * 地址
	 */
	private String address;
	
	/**
	 * keystore
	 */
	private String keystore;
	
	/**
	 * 是否有效：D无效E有效
	 */
	private String valid;
	
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

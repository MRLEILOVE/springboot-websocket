package com.bittrade.pojo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.core.framework.base.model.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * 
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value="w_coin_config")
public class WCoinConfig extends BaseModel<WCoinConfig> {
	
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
		 * 扫描区块的高度（主要查充值数据）
		 */
		public static final String SCAN_BLOCK = "scan_block";
		
		/**
		 * 基础倍数8【基础单位换成聪】ETH是18，有些ETHtoken不是18.需要注意
		 */
		public static final String BASE_MULTIPLE = "base_multiple";
		
		/**
		 * 最低确认数
		 */
		public static final String MIN_CONFIRM = "min_confirm";
		
		/**
		 * boss钱包
		 */
		public static final String BOSS_ADDRESS = "boss_address";
		
		/**
		 * 最低归集数
		 */
		public static final String MIN_COLLECTION_AMOUNT = "min_collection_amount";
		
		/**
		 * 禁用D  启用E
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
	private long id;
	
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
	 * 扫描区块的高度（主要查充值数据）
	 */
	private long scanBlock;
	
	/**
	 * 基础倍数8【基础单位换成聪】ETH是18，有些ETHtoken不是18.需要注意
	 */
	private int baseMultiple;
	
	/**
	 * 最低确认数
	 */
	private int minConfirm;
	
	/**
	 * boss钱包
	 */
	private String bossAddress;
	
	/**
	 * 最低归集数
	 */
	private java.math.BigDecimal minCollectionAmount;
	
	/**
	 * 禁用D  启用E
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

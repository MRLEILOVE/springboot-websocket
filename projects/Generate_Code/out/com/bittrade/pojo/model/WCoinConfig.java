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
 * 
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
	 * 扫描区块的高度（主要查充值数据）
	 */
	private Long scanBlock;
	
	/**
	 * 基础倍数8【基础单位换成聪】ETH是18，有些ETHtoken不是18.需要注意
	 */
	private Integer baseMultiple;
	
	/**
	 * 最低确认数
	 */
	private Integer minConfirm;
	
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
	private java.time.LocalDateTime createTime;
	
	/**
	 * 更新时间
	 */
	private java.time.LocalDateTime updateTime;
	
}

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
 * k线数据
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_kline")
public class TKline extends BaseModel<TKline> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 *
	 */
	public static final class FieldNames {
		
		/**
		 * 主键ID
		 */
		public static final String ID = "id";
		
		/**
		 * 如:btc_usdt
		 */
		public static final String SYMBOL = "symbol";
		
		/**
		 * 最低价格
		 */
		public static final String LOW = "low";
		
		/**
		 * 最高价格
		 */
		public static final String HIGH = "high";
		
		/**
		 * 开盘价格
		 */
		public static final String OPEN = "open";
		
		/**
		 * 收盘价格
		 */
		public static final String CLOSE = "close";
		
		/**
		 * 交易量
		 */
		public static final String VOLUME = "volume";
		
		/**
		 * 时间粒度，以秒为单位，如[60/180/300/900/1800/3600/7200/14400/21600/43200/86400/604800]
		 */
		public static final String GRANULARITY = "granularity";
		
		/**
		 * 开始时间
		 */
		public static final String TIME = "time";
		
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
	 * 主键ID
	 */
	@com.baomidou.mybatisplus.annotation.TableId(value = "id", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
	private Integer id;
	
	/**
	 * 如:btc_usdt
	 */
	private String symbol;
	
	/**
	 * 最低价格
	 */
	private java.math.BigDecimal low;
	
	/**
	 * 最高价格
	 */
	private java.math.BigDecimal high;
	
	/**
	 * 开盘价格
	 */
	private java.math.BigDecimal open;
	
	/**
	 * 收盘价格
	 */
	private java.math.BigDecimal close;
	
	/**
	 * 交易量
	 */
	private java.math.BigDecimal volume;
	
	/**
	 * 时间粒度，以秒为单位，如[60/180/300/900/1800/3600/7200/14400/21600/43200/86400/604800]
	 */
	private Integer granularity;
	
	/**
	 * 开始时间
	 */
	private java.time.LocalDateTime time;
	
	/**
	 * 创建时间
	 */
	private java.time.LocalDateTime createTime;
	
	/**
	 * 更新时间
	 */
	private java.time.LocalDateTime updateTime;
	
}

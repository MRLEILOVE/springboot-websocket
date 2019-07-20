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
 * 行情表
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_ticker")
public class TTicker extends BaseModel<TTicker> {
	
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
		 * 币对名称
		 */
		public static final String SYMBOL = "symbol";
		
		/**
		 * 最新成交价[收盘价]
		 */
		public static final String LAST = "last";
		
		/**
		 * 创建时间
		 */
		public static final String CREATE_TIME = "create_time";
		
	};
	
	/**
	 * 主键ID
	 */
	@com.baomidou.mybatisplus.annotation.TableId(value = "id", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
	private Integer id;
	
	/**
	 * 币对名称
	 */
	private String symbol;
	
	/**
	 * 最新成交价[收盘价]
	 */
	private String last;
	
	/**
	 * 创建时间
	 */
	private java.time.LocalDateTime createTime;
	
}

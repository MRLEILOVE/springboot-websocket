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
 * 
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_payment")
public class TPayment extends BaseModel<TPayment> {
	
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
		public static final String PAY_NAME_CN = "pay_name_cn";
		
		/**
		 * 
		 */
		public static final String PAY_NAME_EN = "pay_name_en";
		
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
	private Integer id;
	
	/**
	 * 
	 */
	private String payNameCn;
	
	/**
	 * 
	 */
	private String payNameEn;
	
	/**
	 * 
	 */
	private java.time.LocalDateTime createTime;
	
	/**
	 * 
	 */
	private java.time.LocalDateTime updateTime;
	
}
